package edu.dronicbest.jokeapp.presentation

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.DrawableRes
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
            it.show(communication)
//            communication.showData(it.getData())
        }
    }

    fun getJoke(): Job = viewModelScope.launch(dispatcher) {
        communication.showState(State.Progress)
        model.getJoke().show(communication)
    }

    fun chooseFavorites(favorites: Boolean) = model.chooseDataSource(favorites)

    fun observe(owner: LifecycleOwner, observer: Observer<BaseViewModel.State>) {
        communication.observe(owner, observer)
    }

    sealed class State {
        abstract fun show(
            process: View,
            button: Button,
            textView: TextView,
            imageButton: ImageButton
        )
        object Progress : State() {
            override fun show(
                process: View,
                button: Button,
                textView: TextView,
                imageButton: ImageButton
            ) {
                process.visibility = View.VISIBLE
                button.isEnabled = false
            }
        }
        data class Initial(val text: String, @DrawableRes val id: Int) : State() {
            override fun show(
                process: View,
                button: Button,
                textView: TextView,
                imageButton: ImageButton
            ) {
                process.visibility = View.INVISIBLE
                button.isEnabled = true
                textView.text = text
                imageButton.setImageResource(id)
            }
        }
    }
}