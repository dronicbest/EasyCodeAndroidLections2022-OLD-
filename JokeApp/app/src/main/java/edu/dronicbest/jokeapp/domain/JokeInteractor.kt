package edu.dronicbest.jokeapp.domain

/**
 * JokeApp
 * @author dronicbest on 17.08.2022
 */
interface JokeInteractor {
    suspend fun getJoke(): Joke
    suspend fun changeFavorites(): Joke
    fun getFavoriteJokes(favorites: Boolean)
}