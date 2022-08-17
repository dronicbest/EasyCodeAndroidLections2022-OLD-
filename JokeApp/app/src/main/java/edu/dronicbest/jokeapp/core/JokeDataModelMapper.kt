package edu.dronicbest.jokeapp.core

/**
 * JokeApp
 * @author dronicbest on 17.08.2022
 */
interface JokeDataModelMapper<T> {
    fun map(id: Int, text: String, punchLine: String, cached: Boolean): T
}