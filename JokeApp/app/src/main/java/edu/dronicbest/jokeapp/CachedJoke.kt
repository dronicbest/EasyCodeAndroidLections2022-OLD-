package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 15.07.2022
 */
interface CachedJoke : ChangeJoke {
    fun saveJoke(joke: Joke)
    fun clear()
}