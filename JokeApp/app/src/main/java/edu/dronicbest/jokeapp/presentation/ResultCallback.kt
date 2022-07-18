package edu.dronicbest.jokeapp.presentation

import edu.dronicbest.jokeapp.presentation.JokeUiModel

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
interface ResultCallback {
    fun provideJoke(jokeUiModel: JokeUiModel)
}