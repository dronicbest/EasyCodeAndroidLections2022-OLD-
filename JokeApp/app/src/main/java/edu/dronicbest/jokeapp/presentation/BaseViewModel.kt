package edu.dronicbest.jokeapp.presentation

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BaseViewModel(
    private val model: Model,
    private val communication: Communication,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    fun changeJokeStatus() = viewModelScope.launch(dispatcher) {
        model.changeJokeStatus()?.let {
            communication.showData(it.getData())
        }
    }

    fun getJoke(): Job = viewModelScope.launch(dispatcher) {
        communication.showData(model.getJoke().getData())
    }

    fun chooseFavorites(favorites: Boolean) = model.chooseDataSource(favorites)

    fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>) {
        communication.observe(owner, observer)
    }
}