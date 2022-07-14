package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
class BaseJokeUiModel(text: String, punchline: String) : JokeUiModel(text, punchline) {
    override fun getIconResId(): Int = R.drawable.baseline_favorite_border_24
}