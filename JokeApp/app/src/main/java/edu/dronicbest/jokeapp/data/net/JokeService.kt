package edu.dronicbest.jokeapp.data.net

import edu.dronicbest.jokeapp.core.Mapper
import edu.dronicbest.jokeapp.data.JokeDataModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
interface JokeService<T : Mapper<JokeDataModel>> {

    fun getJoke() : Call<T>

}