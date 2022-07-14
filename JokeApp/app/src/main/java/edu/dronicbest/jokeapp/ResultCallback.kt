package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
interface ResultCallback {
    fun provideJoke(jokeUiModel: JokeUiModel)
}