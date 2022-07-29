package edu.dronicbest.jokeapp.data.cache

import edu.dronicbest.jokeapp.presentation.FailedJokeUiModel
import edu.dronicbest.jokeapp.data.BaseResultHandler
import edu.dronicbest.jokeapp.data.JokeDataFetcher
import edu.dronicbest.jokeapp.data.Result
import edu.dronicbest.jokeapp.domain.Joke
import edu.dronicbest.jokeapp.presentation.JokeFailure
import edu.dronicbest.jokeapp.presentation.JokeUiModel

/**
 * JokeApp
 * @author dronicbest on 18.07.2022
 */
class CacheResultHandler(
    private val cachedJoke: CachedJoke,
    jokeDataFetcher: JokeDataFetcher<Joke, Unit>,
    private val noCachedJokes: JokeFailure
) : BaseResultHandler<Joke, Unit>(jokeDataFetcher) {

    override fun handleResult(result: Result<Joke, Unit>): JokeUiModel =
        when (result) {
            is Result.Success<Joke> -> result.data.let {
                cachedJoke.saveJoke(it)
                it.toFavoriteJoke()
            }
            is Result.Error -> {
                cachedJoke.clear()
                FailedJokeUiModel(noCachedJokes.getMessage())
            }
        }
}