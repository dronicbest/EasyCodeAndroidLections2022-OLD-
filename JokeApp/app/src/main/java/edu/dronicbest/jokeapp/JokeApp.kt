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
import edu.dronicbest.jokeapp.data.net.NewJokeCloudDataSource
import edu.dronicbest.jokeapp.data.net.NewJokeService
import edu.dronicbest.jokeapp.domain.BaseJokeInteractor
import edu.dronicbest.jokeapp.domain.JokeFailureFactory
import edu.dronicbest.jokeapp.presentation.*
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp : Application() {
    lateinit var viewModel: BaseViewModel

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cacheDataSource = BaseCacheDataSource(BaseRealmProvider(), JokeRealmMapper())
        val resourceManager = BaseResourceManager(this)
        val cloudDataSource = NewJokeCloudDataSource(retrofit.create(NewJokeService::class.java))
        val repository = BaseJokeRepository(cacheDataSource, cloudDataSource, BaseCachedJoke())
        val interactor = BaseJokeInteractor(repository, JokeFailureFactory(resourceManager), JokeSuccessMapper())
        viewModel = BaseViewModel(interactor, BaseCommunication())
    }
}
