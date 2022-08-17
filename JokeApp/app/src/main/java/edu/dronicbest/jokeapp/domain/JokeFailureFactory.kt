package edu.dronicbest.jokeapp.domain

import edu.dronicbest.jokeapp.ResourceManager
import edu.dronicbest.jokeapp.domain.exceptions.GenericError
import edu.dronicbest.jokeapp.domain.exceptions.NoCachedJokesException
import edu.dronicbest.jokeapp.domain.exceptions.NoConnectionException
import edu.dronicbest.jokeapp.domain.exceptions.ServiceUnavailableException
import edu.dronicbest.jokeapp.domain.exceptions.JokeFailure
import edu.dronicbest.jokeapp.domain.exceptions.NoCachedJokes
import edu.dronicbest.jokeapp.domain.exceptions.NoConnection
import edu.dronicbest.jokeapp.domain.exceptions.ServiceUnavailable

/**
 * JokeApp
 * @author dronicbest on 17.08.2022
 */
class JokeFailureFactory(private val resourceManager: ResourceManager) : JokeFailureHandler {
    override fun handle(e: Exception): JokeFailure {
        return when (e) {
            is NoConnectionException -> NoConnection(resourceManager)
            is NoCachedJokesException -> NoCachedJokes(resourceManager)
            is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
            else -> GenericError(resourceManager)
        }
    }
}