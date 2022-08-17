package edu.dronicbest.jokeapp.domain.exceptions

import edu.dronicbest.jokeapp.R
import edu.dronicbest.jokeapp.ResourceManager
import edu.dronicbest.jokeapp.domain.exceptions.JokeFailure

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
class ServiceUnavailable(resourceManager: ResourceManager) : BaseJokeFailure(resourceManager) {
    override fun getMessageResId(): Int {
        return R.string.service_unavailable
    }
}