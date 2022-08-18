package edu.dronicbest.jokeapp.data.net

import retrofit2.Call
import retrofit2.http.GET

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
interface JokeService {
    @GET("https://v2.jokeapi.dev/joke/Any")
    fun getJoke() : Call<JokeServerModel>

    @GET("https://v2.jokeapi.dev/joke/Any")
    fun getNewJoke() : Call<NewJokeServerModel>
}