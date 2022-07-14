package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
sealed class Result<out R, out E> {
    data class Success<out T>(val data: T) : Result<T, Nothing>()
//    data class Error<out S>(val exception: S) : Result<Nothing, S>()
    data class Error<out E>(val exception: E) : Result<Nothing, E>()
}
