package edu.dronicbest.jokeapp

import android.app.Application
import edu.dronicbest.jokeapp.data.cache.BaseCacheDataSource
import edu.dronicbest.jokeapp.data.cache.BaseCachedJoke
import edu.dronicbest.jokeapp.data.cache.BaseRealmProvider
import edu.dronicbest.jokeapp.data.cache.CacheResultHandler
import edu.dronicbest.jokeapp.data.net.BaseCloudDataSource
import edu.dronicbest.jokeapp.data.net.CloudResultHandler
import edu.dronicbest.jokeapp.data.net.JokeService
import edu.dronicbest.jokeapp.presentation.*
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp : Application() {
    lateinit var viewModel: BaseViewModel

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cachedJoke = BaseCachedJoke()
        val cacheDataSource = BaseCacheDataSource(BaseRealmProvider())
        val resourceManager = BaseResourceManager(this)

        viewModel = BaseViewModel(
            BaseModel(
                cacheDataSource,
                CacheResultHandler(
                    cachedJoke,
                    cacheDataSource,
                    NoCachedJokes(resourceManager)
                ),
                CloudResultHandler(
                    cachedJoke,
                    BaseCloudDataSource(retrofit.create(JokeService::class.java)),
                    NoConnection(resourceManager),
                    ServiceUnavailable(resourceManager)
                ),
                cachedJoke
            ),
            BaseCommunication()
        )
    }
}
