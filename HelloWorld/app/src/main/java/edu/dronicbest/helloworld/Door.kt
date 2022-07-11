package edu.dronicbest.helloworld

import android.util.Log

/**
 * HelloWorld
 * @author dronicbest on 11.07.2022
 */
class Door {
    private var mainThingDone = false
    private val logger = LoggingTool()

    fun doMain() {
        if (!mainThingDone) {
            logger.log("main thing done")
            mainThingDone = true
        }
    }
}

class LoggingTool {
    fun log(message: String) {
        Log.d(javaClass.canonicalName, message)
    }
}