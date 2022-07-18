package edu.dronicbest.jokeapp.data.net

import android.util.Log
import edu.dronicbest.jokeapp.data.ErrorType
import edu.dronicbest.jokeapp.data.Result
import java.net.UnknownHostException

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
class BaseCloudDataSource(private val service: JokeService) : CloudDataSource {
    override suspend fun getJoke(): Result<JokeServerModel, ErrorType> {
        return try {
            val result: JokeServerModel = service.getJoke().execute().body()!!
            Log.d("threadLogTag", "currentThread ${Thread.currentThread().name}")
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