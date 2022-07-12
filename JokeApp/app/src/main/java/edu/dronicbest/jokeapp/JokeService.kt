package edu.dronicbest.jokeapp

import retrofit2.Call
import retrofit2.http.GET

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
interface JokeService {
    @GET("https://official-joke-api.appspot.com/random_joke/")
    fun getJoke() : Call<JokeServerModel>
}

interface ServiceCallback {
    fun returnSuccess(data: JokeServerModel)
    fun returnError(type: ErrorType)
}

interface CacheDataSource {
    fun addOrRemove(id: Int, joke: JokeServerModel): Joke
}

interface CloudDataSource {
    fun getJoke(callback: JokeCallback)
}

interface JokeCallback {
    fun provide(joke: JokeServerModel)
    fun fail(error: ErrorType)
}

enum class ErrorType {
    NO_CONNECTION,
    SERVICE_UNAVAILABLE,
    OTHER
}