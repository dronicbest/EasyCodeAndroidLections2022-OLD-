package edu.dronicbest.jokeapp.data.cache

import edu.dronicbest.jokeapp.data.JokeDataModel

/**
 * JokeApp
 * @author dronicbest on 15.07.2022
 */
interface ChangeJoke {
    suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel

    class Empty : ChangeJoke {
        override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel {
            throw IllegalStateException("Empty change joke called")
        }
    }
}