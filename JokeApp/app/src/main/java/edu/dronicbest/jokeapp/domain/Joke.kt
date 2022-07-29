package edu.dronicbest.jokeapp.domain

import edu.dronicbest.jokeapp.presentation.BaseJokeUiModel
import edu.dronicbest.jokeapp.presentation.FavoriteJokeUiModel
import edu.dronicbest.jokeapp.data.cache.ChangeJoke
import edu.dronicbest.jokeapp.data.cache.ChangeJokeStatus
import edu.dronicbest.jokeapp.data.cache.JokeRealm

/**
 * JokeApp
 * @author dronicbest on 14.07.2022
 */
class Joke (
    private val id: Int,
    private val type: String,
    private val text: String,
    private val punchline: String
) : ChangeJoke {

    override suspend fun change(changeJokeStatus: ChangeJokeStatus) = changeJokeStatus.addOrRemove(id, this)
    fun toBaseJoke(): BaseJokeUiModel = BaseJokeUiModel(text, punchline)
    fun toFavoriteJoke(): FavoriteJokeUiModel = FavoriteJokeUiModel(text, punchline)
    fun toJokeRealm(): JokeRealm {
        return JokeRealm().also {
            it.id = id
            it.type = type
            it.text = text
            it.punchLine = punchline
        }
    }

}