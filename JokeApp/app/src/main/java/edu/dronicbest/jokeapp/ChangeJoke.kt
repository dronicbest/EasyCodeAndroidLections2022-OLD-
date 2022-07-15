package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 15.07.2022
 */
interface ChangeJoke {
    suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeUiModel?
}