package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
interface CacheDataSource {
    fun getJoke(jokeCachedCallback: JokeCachedCallback)
    fun addOrRemove(id: Int, joke: Joke): JokeUiModel
}