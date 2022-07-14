package edu.dronicbest.jokeapp

import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
class BaseCloudDataSource(private val service: JokeService) : CloudDataSource {
    override suspend fun getJoke(): Result<JokeServerModel, ErrorType> {
        return try {
            val result = service.getJoke()
            Result.Success(result)
        } catch (e: Exception) {
            val errorType = if (e is UnknownHostException)
                ErrorType.NO_CONNECTION
            else
                ErrorType.SERVICE_UNAVAILABLE
            Result.Error(errorType)
        }
    }
}