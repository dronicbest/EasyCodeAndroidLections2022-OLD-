package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
interface Model {
    suspend fun getJoke(): JokeUiModel
    suspend fun changeJokeStatus(): JokeUiModel?
    fun chooseFavorites(favorites: Boolean)
}