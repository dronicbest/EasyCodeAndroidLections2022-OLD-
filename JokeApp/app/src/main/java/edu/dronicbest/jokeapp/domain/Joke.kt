package edu.dronicbest.jokeapp.domain

import edu.dronicbest.jokeapp.core.Mapper
import edu.dronicbest.jokeapp.domain.exceptions.JokeFailure
import edu.dronicbest.jokeapp.presentation.*

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
sealed class Joke : Mapper<JokeUiModel> {

    class Success(
        private val text: String,
        private val punchline: String,
        private val favorite: Boolean
    ) : Joke() {
        override fun map(): JokeUiModel {
            return if (favorite)
                FavoriteJokeUiModel(text, punchline)
            else
                BaseJokeUiModel(text, punchline)
        }
    }

    class Failed(private val failure: JokeFailure) : Joke() {
        override fun map(): JokeUiModel {
            return FailedJokeUiModel(failure.getMessage())
        }
    }
}