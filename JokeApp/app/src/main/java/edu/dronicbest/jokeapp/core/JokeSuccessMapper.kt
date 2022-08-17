package edu.dronicbest.jokeapp.core

import edu.dronicbest.jokeapp.domain.Joke

/**
 * JokeApp
 * @author dronicbest on 17.08.2022
 */
class JokeSuccessMapper : JokeDataModelMapper<Joke.Success> {
    override fun map(id: Int, text: String, punchLine: String, cached: Boolean): Joke.Success {
        return Joke.Success(text, punchLine, cached)
    }
}