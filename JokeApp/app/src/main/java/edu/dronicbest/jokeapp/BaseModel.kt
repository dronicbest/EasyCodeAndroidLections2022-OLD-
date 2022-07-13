package edu.dronicbest.jokeapp

import retrofit2.Call
import retrofit2.Response

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val resourceManager: ResourceManager
) : Model {
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }
    private var jokeCallback: JokeCallback? = null
    private var cachedJokeServerModel: JokeServerModel? = null
    private var getJokeFromCache = false

    override fun chooseFavorites(cached: Boolean) {
        getJokeFromCache = cached
    }

    override fun getJoke() {
        if (getJokeFromCache) {
            cacheDataSource.getJoke(object : JokeCacheCallback {
                override fun provide(jokeServerModel: JokeServerModel) {
                    cachedJokeServerModel = jokeServerModel
                    jokeCallback?.provide(jokeServerModel.toFavoriteJoke())
                }
                override fun fail() {
                    jokeCallback?.provide(FailedJoke(NoCachedJokes(resourceManager).getMessage()))
                }
            })
        } else {
            cloudDataSource.getJoke(object : JokeCloudCallback {
                override fun provide(joke: JokeServerModel) {
                    cachedJokeServerModel = joke
                    jokeCallback?.provide(joke.toBaseJoke())
                }

                override fun fail(error: ErrorType) {
                    cachedJokeServerModel = null
                    val failure =
                        if (error == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
                    jokeCallback?.provide(FailedJoke(failure.getMessage()))
                }
            })
        }
    }

    override fun init(callback: JokeCallback) {
        this.jokeCallback = callback
    }

    override fun clear() {
        jokeCallback = null
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {
        cachedJokeServerModel?.change(cacheDataSource)?.let {
            jokeCallback.provide(it)
        }
    }

}
