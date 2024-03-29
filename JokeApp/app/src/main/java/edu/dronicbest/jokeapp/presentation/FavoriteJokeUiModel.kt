package edu.dronicbest.jokeapp.presentation

import edu.dronicbest.jokeapp.R

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
class FavoriteJokeUiModel(text: String, punchline: String) : JokeUiModel(text, punchline) {
    override fun getIconResId(): Int = R.drawable.baseline_favorite_24
}