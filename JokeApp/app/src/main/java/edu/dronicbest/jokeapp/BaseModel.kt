package edu.dronicbest.jokeapp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    cloudDataSource: CloudDataSource,
    private val resourceManager: ResourceManager
) : Model {
    private var cachedJoke: Joke? = null
    private val cacheResultHandler by lazy { CacheResultHandler(cacheDataSource) }
    private val cloudResultHandler = CloudResultHandler(cloudDataSource)

    private var currentResultHandler: BaseResultHandler<*, *> = cloudResultHandler

//    TODO
//    private val noConnection by lazy { NoConnection(resourceManager) }
//    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }
//    private val noCachedJokes by lazy { NoCachedJokes(resourceManager) }
//
//
//    private var getJokeFromCache = false

    override fun chooseDataSource(cached: Boolean) {
        currentResultHandler = if (cached) cacheResultHandler else cloudResultHandler
    }

    override suspend fun getJoke(): JokeUiModel = withContext(Dispatchers.IO) {
        return@withContext currentResultHandler.process()
    }

    override suspend fun changeJokeStatus(): JokeUiModel? = cachedJoke?.change(cacheDataSource)

    private interface ResultHandler<S, E> {
        fun handleResult(result: Result<S, E>): JokeUiModel
    }

    private abstract inner class BaseResultHandler<S, E>
        (private val jokeDataFetcher: JokeDataFetcher<S, E>) {

        suspend fun process(): JokeUiModel {
            return handleResult(jokeDataFetcher.getJoke())
        }

        protected abstract fun handleResult(result: Result<S, E>): JokeUiModel
    }

    private inner class CloudResultHandler(
        private val cachedJoke: CachedJoke,
        jokeDataFetcher: JokeDataFetcher<JokeServerModel, ErrorType>,
        private val noConnection: JokeFailure,
        private val serviceUnavailable: JokeFailure
    ) : BaseResultHandler<JokeServerModel, ErrorType>(jokeDataFetcher) {

        override fun handleResult(result: Result<JokeServerModel, ErrorType>): JokeUiModel =
            when (result) {
                is Result.Success<JokeServerModel> -> {
                    result.data.toJoke().let {
                        cachedJoke.saveJoke(it)
                        it.toBaseJoke()
                    }
                }
                is Result.Error<ErrorType> -> {
                    cachedJoke.clear()
                    val failure = if (result.exception == ErrorType.NO_CONNECTION)
                        noConnection
                    else
                        serviceUnavailable
                    FailedJokeUiModel(failure.getMessage())
                }
            }
    }

    private inner class CacheResultHandler(
        private val cachedJoke: CachedJoke,
        jokeDataFetcher: JokeDataFetcher<Joke, Unit>,
        private val noCachedJokes: JokeFailure
    ) : BaseResultHandler<Joke, Unit>(jokeDataFetcher) {

        override fun handleResult(result: Result<Joke, Unit>): JokeUiModel =
            when (result) {
                is Result.Success<Joke> -> result.data.let {
                    cachedJoke.saveJoke(it)
                    it.toFavoriteJoke()
                }
                is Result.Error -> {
                    cachedJoke.clear()
                    FailedJokeUiModel(noCachedJokes.getMessage())
                }
            }
    }
}
