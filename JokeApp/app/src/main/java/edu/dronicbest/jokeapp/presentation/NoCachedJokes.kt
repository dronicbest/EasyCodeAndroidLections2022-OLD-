package edu.dronicbest.jokeapp.presentation

import edu.dronicbest.jokeapp.R
import edu.dronicbest.jokeapp.ResourceManager

/**
 * JokeApp
 * @author dronicbest on 13.07.2022
 */
class NoCachedJokes(private val resourceManager: ResourceManager) : JokeFailure {
    override fun getMessage(): String = resourceManager.getString(R.string.no_cached_jokes)
}