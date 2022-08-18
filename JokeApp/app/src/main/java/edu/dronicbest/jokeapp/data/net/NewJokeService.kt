package edu.dronicbest.jokeapp.data.net

import retrofit2.Call
import retrofit2.http.GET

/**
 * JokeApp
 * @author dronicbest on 18.08.2022
 */
interface NewJokeService {
    @GET("https://v2.jokeapi.dev/joke/Any")
    fun getJoke(): Call<NewJokeServerModel>
}