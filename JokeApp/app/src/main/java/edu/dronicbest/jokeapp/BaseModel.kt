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

    override fun getJoke() {
        cloudDataSource.getJoke(object : Joke)
    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }

}
