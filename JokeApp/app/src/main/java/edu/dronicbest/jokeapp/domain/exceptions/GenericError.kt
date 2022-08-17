package edu.dronicbest.jokeapp.domain.exceptions

import edu.dronicbest.jokeapp.R
import edu.dronicbest.jokeapp.ResourceManager

/**
 * JokeApp
 * @author dronicbest on 17.08.2022
 */
class GenericError(resourceManager: ResourceManager) : BaseJokeFailure(resourceManager) {
    override fun getMessageResId(): Int {
        return R.string.generic_fail_message
    }
}