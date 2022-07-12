package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
class Joke(private val text: String, private val punchline: String) {
    fun getJokeUi() = "$text\n$punchline"
}