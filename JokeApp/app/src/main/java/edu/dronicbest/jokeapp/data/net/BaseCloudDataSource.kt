package edu.dronicbest.jokeapp.data.net

import edu.dronicbest.jokeapp.core.Mapper
import edu.dronicbest.jokeapp.data.JokeDataModel
import edu.dronicbest.jokeapp.domain.exceptions.NoConnectionException
import edu.dronicbest.jokeapp.domain.exceptions.ServiceUnavailableException
import retrofit2.Call
import java.net.UnknownHostException

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
abstract class BaseCloudDataSource<T : Mapper<JokeDataModel>> : CloudDataSource {

    protected abstract fun getJokeServerModel(): Call<T>

    override suspend fun getJoke(): JokeDataModel {
        try {
            return getJokeServerModel().execute().body()!!.map()
        } catch (e: Exception) {
            if (e is UnknownHostException)
                throw NoConnectionException()
            else
                throw ServiceUnavailableException()
        }
    }
}