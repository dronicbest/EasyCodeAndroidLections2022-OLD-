package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 15.07.2022
 */
interface JokeDataFetcher<S, E> {
    suspend fun getJoke(): Result<S, E>
}