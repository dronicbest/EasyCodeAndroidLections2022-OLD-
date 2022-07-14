package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
interface JokeCachedCallback {
    fun provide(joke: Joke)
    fun fail()
}