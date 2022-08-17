package edu.dronicbest.jokeapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.dronicbest.jokeapp.domain.JokeInteractor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BaseViewModel(
    private val interactor: JokeInteractor,
    private val communication: Communication,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    fun getJoke(): Job = viewModelScope.launch(dispatcher) {
        communication.showState(State.Progress)
        interactor.getJoke().map().show(communication)
    }

    fun changeJokeStatus() = viewModelScope.launch(dispatcher) {
        if (communication.isState(State.INITIAL))
            interactor.changeFavorites().map().show(communication)
    }

    fun chooseFavorites(favorites: Boolean) = interactor.getFavoriteJokes(favorites)

    fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        communication.observe(owner, observer)
    }
}