package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
interface CacheDataSource {
    suspend fun getJoke(): Result<Joke, Unit>
    suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel
}