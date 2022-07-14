package edu.dronicbest.jokeapp

import androidx.annotation.DrawableRes

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
interface DataCallback {
    fun provideText(text: String)
    fun provideIconRes(@DrawableRes id: Int)
}