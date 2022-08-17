package edu.dronicbest.jokeapp.data.net

import edu.dronicbest.jokeapp.data.JokeDataModel
import edu.dronicbest.jokeapp.domain.exceptions.NoConnectionException
import edu.dronicbest.jokeapp.domain.exceptions.ServiceUnavailableException
import java.net.UnknownHostException

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
class BaseCloudDataSource(private val service: JokeService) : CloudDataSource {
    override suspend fun getJoke(): JokeDataModel {
        try {
            return service.getJoke().execute().body()!!.map()
        } catch (e: Exception) {
            if (e is UnknownHostException)
                throw NoConnectionException()
            else
                throw ServiceUnavailableException()
        }
    }
}