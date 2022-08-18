package edu.dronicbest.jokeapp.data.net

import com.google.gson.annotations.SerializedName
import edu.dronicbest.jokeapp.core.Mapper
import edu.dronicbest.jokeapp.data.JokeDataModel

/**
 * JokeApp
 * @author dronicbest on 18.08.2022
 */
class NewJokeServerModel (
    @SerializedName("id")
    private val id: Int,
    @SerializedName("setup")
    private val text: String,
    @SerializedName("delivery")
    private val punchline: String
    ) : Mapper<JokeDataModel> {

        override fun map() = JokeDataModel(id, text, punchline)
}