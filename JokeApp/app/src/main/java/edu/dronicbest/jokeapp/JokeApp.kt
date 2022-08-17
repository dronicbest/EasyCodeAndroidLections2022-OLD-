package edu.dronicbest.jokeapp

import android.app.Application
import edu.dronicbest.jokeapp.core.JokeRealmMapper
import edu.dronicbest.jokeapp.core.JokeSuccessMapper
import edu.dronicbest.jokeapp.data.BaseJokeRepository
import edu.dronicbest.jokeapp.data.cache.BaseCacheDataSource
import edu.dronicbest.jokeapp.data.cache.BaseCachedJoke
import edu.dronicbest.jokeapp.data.cache.BaseRealmProvider
import edu.dronicbest.jokeapp.data.net.BaseCloudDataSource
import edu.dronicbest.jokeapp.data.net.JokeService
import edu.dronicbest.jokeapp.domain.BaseJokeInteractor
import edu.dronicbest.jokeapp.domain.JokeFailureFactory
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

        val cacheDataSource = BaseCacheDataSource(BaseRealmProvider(), JokeRealmMapper())
        val resourceManager = BaseResourceManager(this)
        val cloudDataSource = BaseCloudDataSource(retrofit.create(JokeService::class.java))
        val repository = BaseJokeRepository(cacheDataSource, cloudDataSource, BaseCachedJoke())
        val interactor = BaseJokeInteractor(repository, JokeFailureFactory(resourceManager), JokeSuccessMapper())
        viewModel = BaseViewModel(interactor, BaseCommunication())
    }
}
