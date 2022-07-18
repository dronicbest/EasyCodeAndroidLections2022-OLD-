package edu.dronicbest.jokeapp.data.net

import edu.dronicbest.jokeapp.data.ErrorType
import edu.dronicbest.jokeapp.presentation.FailedJokeUiModel
import edu.dronicbest.jokeapp.data.BaseResultHandler
import edu.dronicbest.jokeapp.data.JokeDataFetcher
import edu.dronicbest.jokeapp.data.Result
import edu.dronicbest.jokeapp.data.cache.CachedJoke
import edu.dronicbest.jokeapp.presentation.JokeFailure
import edu.dronicbest.jokeapp.presentation.JokeUiModel

/**
 * JokeApp
 * @author dronicbest on 18.07.2022
 */
class CloudResultHandler(
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