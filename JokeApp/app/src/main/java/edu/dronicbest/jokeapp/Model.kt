package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
interface Model {
    suspend fun getJoke(): JokeUiModel
    fun init(callback: JokeCallback)
    fun clear()
    suspend fun changeJokeStatus(): JokeUiModel?
    fun chooseFavorites(favorites: Boolean)
}