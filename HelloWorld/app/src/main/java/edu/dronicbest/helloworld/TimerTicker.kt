package edu.dronicbest.helloworld

import android.text.format.Time
import java.util.*

/**
 * HelloWorld
 * @author dronicbest on 11.07.2022
 */
class TimerTicker : TimeTicker {
    private var callback: TimeTicker.Callback? = null
    private var timer: Timer? = null
    private val timerTask
        get() = object : TimerTask() {
            override fun run() {
                callback?.tick()
            }
        }

    override fun start(callback: TimeTicker.Callback, period: Long) {
        this.callback = callback
        timer = Timer()
        timer?.scheduleAtFixedRate(timerTask, 0, period)
    }

    override fun stop() {
        callback = null
        timer?.cancel()
        timer = null
    }
}