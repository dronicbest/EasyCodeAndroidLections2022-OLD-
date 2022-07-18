package edu.dronicbest.jokeapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * JokeApp
 * @author dronicbest on 18.07.2022
 */
class BaseCommunication : Communication {
    private val liveData = MutableLiveData<Pair<String, Int>>()

    override fun showData(data: Pair<String, Int>) {
        liveData.value = data
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<Pair<String, Int>>) {
        liveData.observe(owner, observer)
    }
}