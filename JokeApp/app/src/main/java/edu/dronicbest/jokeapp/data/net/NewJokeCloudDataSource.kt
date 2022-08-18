package edu.dronicbest.jokeapp.data.net

import retrofit2.Call

/**
 * JokeApp
 * @author dronicbest on 18.08.2022
 */
class NewJokeCloudDataSource(private val service: NewJokeService) :
    BaseCloudDataSource<NewJokeServerModel>() {
    override fun getJokeServerModel(): Call<NewJokeServerModel> {
        return service.getJoke()
    }
}