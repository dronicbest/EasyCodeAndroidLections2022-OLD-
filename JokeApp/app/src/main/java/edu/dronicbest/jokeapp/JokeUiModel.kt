package edu.dronicbest.jokeapp

import androidx.annotation.DrawableRes

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
abstract class JokeUiModel(private val text: String, private val punchline: String) {
    protected fun text() = "$text\n$punchline"

    @DrawableRes
    protected abstract fun getIconResId(): Int

    fun map(callback: DataCallback) = callback.run {
        provideText(text())
        provideIconRes(getIconResId())
    }
}