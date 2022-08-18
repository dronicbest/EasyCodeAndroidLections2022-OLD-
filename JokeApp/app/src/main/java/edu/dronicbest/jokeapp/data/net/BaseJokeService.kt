package edu.dronicbest.jokeapp.data.net

import retrofit2.Call
import retrofit2.http.GET

/**
 * JokeApp
 * @author dronicbest on 18.08.2022
 */
interface BaseJokeService {
    @GET("https://official-joke-api.appspot.com/random_joke")
    fun getJoke(): Call<JokeServerModel>
}