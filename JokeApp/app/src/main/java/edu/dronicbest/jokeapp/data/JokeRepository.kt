package edu.dronicbest.jokeapp.data

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
interface JokeRepository {
    suspend fun getJoke(): JokeDataModel
    suspend fun changeJokeStatus(): JokeDataModel
    fun chooseDataSource(cached: Boolean)
}