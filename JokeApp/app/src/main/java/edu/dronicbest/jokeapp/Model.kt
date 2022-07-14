package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
interface Model {
    fun getJoke()
    fun init(callback: JokeCallback)
    fun clear()
    fun changeJokeStatus(jokeCallback: JokeCallback)
    fun chooseFavorites(favorites: Boolean)
}