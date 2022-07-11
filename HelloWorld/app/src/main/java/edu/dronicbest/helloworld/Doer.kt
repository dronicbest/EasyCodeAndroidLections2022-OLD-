package edu.dronicbest.helloworld

import android.util.Log

/**
 * HelloWorld
 * @author dronicbest on 11.07.2022
 */
class Doer(private val logger: Logging) {
    private var mainThingDone = false

    fun doMain() {
        if (!mainThingDone) {
            logger.log("main thing done")
            mainThingDone = true
        }
    }
}