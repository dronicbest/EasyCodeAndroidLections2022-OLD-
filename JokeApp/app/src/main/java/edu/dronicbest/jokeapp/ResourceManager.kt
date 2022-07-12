package edu.dronicbest.jokeapp

import android.content.Context
import androidx.annotation.StringRes

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
interface ResourceManager {
    fun getString(@StringRes stringResIdRes: Int): String
}

class BaseResourceManager(private val context: Context) : ResourceManager {
    override fun getString(stringResIdRes: Int): String = context.getString(stringResIdRes)
}