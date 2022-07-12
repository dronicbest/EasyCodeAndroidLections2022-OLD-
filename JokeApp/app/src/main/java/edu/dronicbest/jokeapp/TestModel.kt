package edu.dronicbest.jokeapp

/**
 * JokeApp
 * @author dronicbest on 12.07.2022
 */
class TestModel : Model<Any, Any> {
    private var callback: ResultCallback<Any, Any>? = null
    private var count = 1

    override fun getJoke() {
        Thread {
            Thread.sleep(1000)
            if (count % 2 == 0) {
                callback?.provideSuccess("success")
            } else {
                callback?.provideError("error")
            }
            count++
        }.start()
    }

    override fun init(callback: ResultCallback<Any, Any>) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }

}