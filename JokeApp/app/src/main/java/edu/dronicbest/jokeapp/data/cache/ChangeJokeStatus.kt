package edu.dronicbest.jokeapp.data.cache

import edu.dronicbest.jokeapp.domain.Joke
import edu.dronicbest.jokeapp.presentation.JokeUiModel

/**
 * JokeApp
 * @author dronicbest on 15.07.2022
 */
interface ChangeJokeStatus {
    suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel?
}