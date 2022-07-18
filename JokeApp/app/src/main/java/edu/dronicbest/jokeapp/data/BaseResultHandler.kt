package edu.dronicbest.jokeapp.data

import edu.dronicbest.jokeapp.presentation.JokeUiModel

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