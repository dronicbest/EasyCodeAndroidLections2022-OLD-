package edu.dronicbest.helloworld

/**
 * HelloWorld
 * @author dronicbest on 11.07.2022
 */
interface TimeTicker {
    fun start(callback: Callback, period: Long = 1000)
    fun stop()

    interface Callback {
        fun tick()
    }
}