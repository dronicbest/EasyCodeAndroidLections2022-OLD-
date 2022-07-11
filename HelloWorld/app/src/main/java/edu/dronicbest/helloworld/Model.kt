package edu.dronicbest.helloworld

import java.util.*

class Model(
    private val dataSource: DataSource,
    private val timeTicker: TimeTicker
) {

    private val tickerCallback
        get() = object : TimeTicker.Callback {
            override fun tick() {
                count++
                callback?.updateText(count.toString())
            }
        }

    private var callback: TextCallback? = null
    private var count = -1
    private val timerTask
        get() = object : TimerTask() {
            override fun run() {
                callback?.updateText(count.toString())
                count++
            }
        }

    fun start(textCallback: TextCallback) {
        callback = textCallback
        if (count < 0) count = dataSource.getInt(COUNTER_KEY)
        timeTicker.start(tickerCallback)
    }

    fun stop() {
        dataSource.saveInt(COUNTER_KEY, count)
        timeTicker.stop()
    }

    companion object {
        private const val COUNTER_KEY = "counterKey"
    }
}