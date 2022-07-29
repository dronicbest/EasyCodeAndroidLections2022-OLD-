package edu.dronicbest.jokeapp.presentation

import edu.dronicbest.jokeapp.data.ErrorType
import edu.dronicbest.jokeapp.data.net.JokeServerModel

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
interface ServiceCallback {
    fun returnSuccess(data: JokeServerModel)
    fun returnError(type: ErrorType)
}