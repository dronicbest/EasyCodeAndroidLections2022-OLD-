package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 18.07.2022
 */
abstract class BaseResultHandler<S, E>
    (private val jokeDataFetcher: JokeDataFetcher<S, E>) {

    suspend fun process(): JokeUiModel {
        return handleResult(jokeDataFetcher.getJoke())
    }

    protected abstract fun handleResult(result: Result<S, E>): JokeUiModel
}