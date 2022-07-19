package edu.dronicbest.jokeapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

/**
 * JokeApp
 * @author dronicbest on 18.07.2022
 */
interface Communication {
    fun showState(state: BaseViewModel.State)
    fun observe(owner: LifecycleOwner, observer: Observer<BaseViewModel.State>)
}