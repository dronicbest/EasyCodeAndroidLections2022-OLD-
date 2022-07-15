package edu.dronicbest.jokeapp

import com.google.gson.annotations.SerializedName

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