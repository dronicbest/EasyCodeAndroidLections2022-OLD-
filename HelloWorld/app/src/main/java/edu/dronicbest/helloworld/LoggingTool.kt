package edu.dronicbest.helloworld

import android.util.Log

/**
 * HelloWorld
 * @author dronicbest on 11.07.2022
 */
class LoggingTool : Logging {
    override fun log(message: String) {
        Log.d(javaClass.canonicalName, message)
    }
}

interface Logging {
    fun log(message: String)
}