package edu.dronicbest.jokeapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

/**
 * JokeApp
 * @author dronicbest on 18.07.2022
 */
interface Communication {
    fun showData(data: Pair<String, Int>)
    fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>)
}