package edu.dronicbest.jokeapp.data

/**
 * JokeApp
 * @author dronicbest on 15.07.2022
 */
interface JokeDataFetcher {
    suspend fun getJoke(): JokeDataModel
}