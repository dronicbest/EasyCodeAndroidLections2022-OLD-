package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 15.07.2022
 */
interface ChangeJokeStatus {
    suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel
}