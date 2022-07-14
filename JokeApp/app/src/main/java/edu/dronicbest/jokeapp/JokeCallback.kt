package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
interface JokeCallback {
    fun provide(jokeUiModel: JokeUiModel)
//    fun fail(error: ErrorType)
}