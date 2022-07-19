package edu.dronicbest.jokeapp.presentation

import androidx.annotation.DrawableRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.dronicbest.jokeapp.presentation.custom_views.EnableView
import edu.dronicbest.jokeapp.presentation.custom_views.ShowImage
import edu.dronicbest.jokeapp.presentation.custom_views.ShowText
import edu.dronicbest.jokeapp.presentation.custom_views.ShowView
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
            it.show(communication)
        }
    }

    fun getJoke(): Job = viewModelScope.launch(dispatcher) {
        communication.showState(State.Progress)
        model.getJoke().show(communication)
    }

    fun chooseFavorites(favorites: Boolean) = model.chooseDataSource(favorites)

    fun observe(owner: LifecycleOwner, observer: Observer<State>) {
        communication.observe(owner, observer)
    }

    sealed class State {
        abstract fun show(
            process: ShowView,
            button: EnableView,
            textView: ShowText,
            imageButton: ShowImage
        )

        object Progress : State() {
            override fun show(
                process: ShowView,
                button: EnableView,
                textView: ShowText,
                imageButton: ShowImage
            ) {
                process.show(true)
                button.enable(false)
            }
        }

        data class Initial(val text: String, @DrawableRes val id: Int) : State() {
            override fun show(
                process: ShowView,
                button: EnableView,
                textView: ShowText,
                imageButton: ShowImage
            ) {
                process.show(false)
                button.enable(true)
                textView.show(text)
                imageButton.show(id)
            }
        }

    }
}