package edu.dronicbest.helloworld

import java.util.*

class Model {
    private var timer: Timer? = null
    private var callback: TextCallback? = null
    private var count = 0
    private val timerTask = object : TimerTask() {
        override fun run() {
            count++
            callback?.updateText(count.toString())
        }
    }

    fun start(textCallback: TextCallback) {
        callback = textCallback
        if (timer == null) {
            timer = Timer()
            timer?.scheduleAtFixedRate(timerTask, 1000, 1000)
        }
    }
}