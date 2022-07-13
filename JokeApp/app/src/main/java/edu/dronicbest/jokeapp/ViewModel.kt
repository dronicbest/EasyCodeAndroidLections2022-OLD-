package edu.dronicbest.jokeapp

import androidx.annotation.DrawableRes

class ViewModel(private val model: Model) {

    private var dataCallback: DataCallback? = null

    private val jokeCallback = object : JokeCallback {
        override fun provide(joke: Joke) {
            dataCallback?.let { joke.map(it) }
        }
    }

    fun init(callback: DataCallback) {
        dataCallback = callback
        model.init(jokeCallback)
    }

    fun getJoke() {
        model.getJoke()
    }

    fun clear() {
        dataCallback = null
        model.clear()
    }

    fun changeJokeStatus() {
        model.changeJokeStatus(jokeCallback)
    }

    fun chooseFavorites(favorites: Boolean) {
        model.chooseFavorites(favorites)
    }
}

interface Model {
    fun getJoke()
    fun init(callback: JokeCallback)
    fun clear()
    fun changeJokeStatus(jokeCallback: JokeCallback)
    fun chooseFavorites(favorites: Boolean)
}

interface ResultCallback {
    fun provideJoke(joke: Joke)
}

interface DataCallback {
    fun provideText(text: String)
    fun provideIconRes(@DrawableRes id: Int)
}