package edu.dronicbest.jokeapp

import android.app.Application
import com.google.gson.Gson
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp : Application() {
    lateinit var viewModel: ViewModel

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

        viewModel = ViewModel(
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
            )
        )
    }
}
