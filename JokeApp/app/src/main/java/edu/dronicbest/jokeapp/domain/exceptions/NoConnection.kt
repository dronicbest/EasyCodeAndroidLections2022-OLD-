package edu.dronicbest.jokeapp.domain.exceptions

import edu.dronicbest.jokeapp.R
import edu.dronicbest.jokeapp.ResourceManager

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
class NoConnection(resourceManager: ResourceManager) : BaseJokeFailure(resourceManager) {
    override fun getMessageResId(): Int {
        return R.string.no_connection
    }
}