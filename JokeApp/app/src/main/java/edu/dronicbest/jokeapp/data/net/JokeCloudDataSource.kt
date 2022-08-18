package edu.dronicbest.jokeapp.data.net

import retrofit2.Call

/**
 * JokeApp
 * @author dronicbest on 18.08.2022
 */
class JokeCloudDataSource(private val service: BaseJokeService) :
    BaseCloudDataSource<JokeServerModel>() {
    override fun getJokeServerModel(): Call<JokeServerModel> {
        return service.getJoke()
    }
}