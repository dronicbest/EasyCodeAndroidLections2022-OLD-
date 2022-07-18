package edu.dronicbest.jokeapp.data.cache

import edu.dronicbest.jokeapp.domain.Joke

/**
 * JokeApp
 * @author dronicbest on 15.07.2022
 */
interface CachedJoke : ChangeJoke {
    fun saveJoke(joke: Joke)
    fun clear()
}