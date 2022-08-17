package edu.dronicbest.jokeapp.data.net

import com.google.gson.annotations.SerializedName
import edu.dronicbest.jokeapp.core.Mapper
import edu.dronicbest.jokeapp.data.JokeDataModel
import edu.dronicbest.jokeapp.domain.Joke

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
data class JokeServerModel (
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
)  : Mapper<JokeDataModel> {

    override fun map() = if (joke == null)
        JokeDataModel(id, setup, punchline)
    else
        JokeDataModel(id, joke, punchline)

}
