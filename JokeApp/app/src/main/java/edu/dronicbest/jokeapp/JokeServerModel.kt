package edu.dronicbest.jokeapp

import com.google.gson.annotations.SerializedName

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
data class JokeServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("type")
    private val type: String,
    @SerializedName("joke")
    private val joke: String,
    @SerializedName("setup")
    private val setup: String,
    @SerializedName("category")
    private val punchline: String
) {
    fun toBaseJoke(): BaseJoke = if (joke == null)
            BaseJoke(setup, punchline)
        else
            BaseJoke(joke, punchline)

    fun toFavoriteJoke(): FavoriteJoke = if (joke == null)
        FavoriteJoke(setup, punchline)
    else
        FavoriteJoke(joke, punchline)

    fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(id, this)
}
