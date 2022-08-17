package edu.dronicbest.jokeapp.domain.exceptions

import androidx.annotation.StringRes
import edu.dronicbest.jokeapp.ResourceManager

/**
 * JokeApp
 * @author dronicbest on 17.08.2022
 */
abstract class BaseJokeFailure(private val resourceManager: ResourceManager) : JokeFailure {
    @StringRes
    protected abstract fun getMessageResId(): Int
    override fun getMessage(): String {
        return resourceManager.getString(getMessageResId())
    }
}