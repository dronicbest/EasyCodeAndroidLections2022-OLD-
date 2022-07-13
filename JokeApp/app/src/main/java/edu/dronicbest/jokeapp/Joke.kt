package edu.dronicbest.jokeapp

import androidx.annotation.DrawableRes

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
abstract class Joke(private val text: String, private val punchline: String) {
    protected fun getJokeUi() = "$text\n$punchline"

    @DrawableRes
    protected abstract fun getIconResId(): Int

    fun map(callback: DataCallback) = callback.run {
        provideText(getJokeUi())
        provideIconRes(getIconResId())
    }
}

class BaseJoke(text: String, punchline: String) : Joke(text, punchline) {
    override fun getIconResId(): Int = R.drawable.baseline_favorite_border_24
}

class FavoriteJoke(text: String, punchline: String) : Joke(text, punchline) {
    override fun getIconResId(): Int = R.drawable.baseline_favorite_24
}

class FailedJoke(text: String, punchline: String = "") : Joke(text, punchline) {
    override fun getIconResId(): Int = 0
}