package edu.dronicbest.jokeapp.presentation

import edu.dronicbest.jokeapp.data.BaseResultHandler
import edu.dronicbest.jokeapp.data.Result
import edu.dronicbest.jokeapp.data.cache.CacheDataSource
import edu.dronicbest.jokeapp.data.cache.CacheResultHandler
import edu.dronicbest.jokeapp.data.cache.CachedJoke
import edu.dronicbest.jokeapp.data.net.CloudResultHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cacheResultHandler: CacheResultHandler,
    private val cloudResultHandler: CloudResultHandler,
    private val cachedJoke: CachedJoke
) : Model {
    private var currentResultHandler: BaseResultHandler<*, *> = cloudResultHandler

    override fun chooseDataSource(cached: Boolean) {
        currentResultHandler = if (cached) cacheResultHandler else cloudResultHandler
    }

    override suspend fun getJoke(): JokeUiModel = withContext(Dispatchers.IO) {
        return@withContext currentResultHandler.process()
    }

    override suspend fun changeJokeStatus(): JokeUiModel? = cachedJoke.change(cacheDataSource)

    private interface ResultHandler<S, E> {
        fun handleResult(result: Result<S, E>): JokeUiModel
    }
}
