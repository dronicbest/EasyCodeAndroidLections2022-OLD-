package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
class FailedJokeUiModel(text: String, punchline: String = "") : JokeUiModel(text, punchline) {
    override fun getIconResId(): Int = 0
}