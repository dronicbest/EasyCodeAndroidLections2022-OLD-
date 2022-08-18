package edu.dronicbest.jokeapp.domain.exceptions

import edu.dronicbest.jokeapp.R
import edu.dronicbest.jokeapp.ResourceManager

/**
 * JokeApp
 * @author dronicbest on 13.07.2022
 */
class NoCachedJokes(resourceManager: ResourceManager) : BaseJokeFailure(resourceManager) {
    override fun getMessageResId(): Int {
        return R.string.no_cached_jokes
    }
}