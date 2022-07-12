package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
interface JokeService {
    fun getJoke(callback: ServiceCallback)
}

interface ServiceCallback {
    fun returnSuccess(data: JokeDTO)
    fun returnError(type: ErrorType)
}

enum class ErrorType {
    NO_CONNECTION,
    OTHER
}