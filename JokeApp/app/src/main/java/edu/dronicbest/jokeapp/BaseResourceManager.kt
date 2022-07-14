package edu.dronicbest.jokeapp

import android.content.Context

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
class BaseResourceManager(private val context: Context) : ResourceManager {
    override fun getString(stringResIdRes: Int): String = context.getString(stringResIdRes)
}