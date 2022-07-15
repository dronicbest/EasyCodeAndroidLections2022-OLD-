package edu.dronicbest.jokeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ViewModel(private val model: Model) : ViewModel() {

    private var dataCallback: DataCallback? = null

    fun init(callback: DataCallback) {
        dataCallback = callback
    }

    fun getJoke(): Job = viewModelScope.launch {
        val uiModel = model.getJoke()
        dataCallback?.let { uiModel.map(it) }
    }

    fun clear() {
        dataCallback = null
    }

    fun changeJokeStatus() = viewModelScope.launch {
        val uiModel = model.changeJokeStatus()
        dataCallback?.let {
            uiModel?.map(it)
        }
    }

    fun chooseFavorites(favorites: Boolean) {
        model.chooseDataSource(favorites)
    }
}