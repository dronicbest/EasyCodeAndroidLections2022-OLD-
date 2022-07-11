package edu.dronicbest.helloworld

import android.util.Log
import java.util.*

class Model(private val dataSource: DataSource) {
    companion object {
        private const val COUNTER_KEY = "counterKey"
    }

    private var timer: Timer? = null
    private var callback: TextCallback? = null
    private var count = -1
    private val timerTask
        get() = object : TimerTask() {
            override fun run() {
                count++
                callback?.updateText(count.toString())
            }
        }

    fun start(textCallback: TextCallback) {
        callback = textCallback
        if (count < 0) count = dataSource.getInt(COUNTER_KEY)
        timer = Timer()
        timer?.scheduleAtFixedRate(timerTask, 0, 1000)
    }

    fun stop() {
        Log.d(TAG, "    stop: with count $count")
        dataSource.saveInt(COUNTER_KEY, count)
        timer?.cancel()
        timer = null
    }
}