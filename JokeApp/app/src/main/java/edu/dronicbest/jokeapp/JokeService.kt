package edu.dronicbest.jokeapp

import retrofit2.Call
import retrofit2.http.GET

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
interface JokeService {
    @GET("https://v2.jokeapi.dev/joke/Any")
    suspend fun getJoke() : JokeServerModel
}