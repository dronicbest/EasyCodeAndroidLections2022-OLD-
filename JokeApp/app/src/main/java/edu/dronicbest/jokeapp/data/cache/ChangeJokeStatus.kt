package edu.dronicbest.jokeapp.data.cache

import edu.dronicbest.jokeapp.data.JokeDataModel

/**
 * JokeApp
 * @author dronicbest on 15.07.2022
 */
interface ChangeJokeStatus {
    suspend fun addOrRemove(id: Int, joke: JokeDataModel): JokeDataModel
}