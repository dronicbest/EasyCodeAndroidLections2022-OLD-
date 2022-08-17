package edu.dronicbest.jokeapp.domain

import edu.dronicbest.jokeapp.domain.exceptions.JokeFailure

/**
 * JokeApp
 * @author dronicbest on 17.08.2022
 */
interface JokeFailureHandler {
    fun handle(e: Exception): JokeFailure
}