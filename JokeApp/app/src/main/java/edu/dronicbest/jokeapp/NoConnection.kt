package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
class NoConnection(private val resourceManager: ResourceManager) : JokeFailure {
    override fun getMessage(): String = resourceManager.getString(R.string.no_connection)
}