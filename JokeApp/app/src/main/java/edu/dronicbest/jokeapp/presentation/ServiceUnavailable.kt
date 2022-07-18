package edu.dronicbest.jokeapp.presentation

import edu.dronicbest.jokeapp.R
import edu.dronicbest.jokeapp.ResourceManager

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
class ServiceUnavailable(private val resourceManager: ResourceManager) : JokeFailure {
    override fun getMessage(): String = resourceManager.getString(R.string.service_unavailable)
}