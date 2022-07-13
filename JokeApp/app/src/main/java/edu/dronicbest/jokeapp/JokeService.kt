package edu.dronicbest.jokeapp

import retrofit2.Call
import retrofit2.http.GET

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
interface JokeService {
    @GET("https://v2.jokeapi.dev/joke/Any")
    fun getJoke() : Call<JokeServerModel>
}

interface ServiceCallback {
    fun returnSuccess(data: JokeServerModel)
    fun returnError(type: ErrorType)
}

interface CacheDataSource {
    fun getJoke(jokeCacheCallback: JokeCacheCallback)
    fun addOrRemove(id: Int, joke: JokeServerModel): Joke
}

interface CloudDataSource {
    fun getJoke(callback: JokeCloudCallback)
}

interface JokeCallback {
    fun provide(joke: Joke)
//    fun fail(error: ErrorType)
}

interface JokeCloudCallback {
    fun provide(joke: JokeServerModel)
    fun fail(error: ErrorType)
}

interface JokeCacheCallback {
    fun provide(jokeServerModel: JokeServerModel)
    fun fail()
}

enum class ErrorType {
    NO_CONNECTION,
    SERVICE_UNAVAILABLE,
    OTHER
}