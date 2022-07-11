package edu.dronicbest.helloworld

import org.junit.Assert.*
import org.junit.Test

/**
 * HelloWorld
 * @author dronicbest on 11.07.2022
 */
class ModelTest {

    @Test
    fun test_start_with_saved_value() {
        val testDataSource = TestDataSource()
        val timeTicker = TestTimeTicker()
        val model = Model(testDataSource, timeTicker)
        val callback = TestCallback()
        testDataSource.saveInt("", 5)
        model.start(callback)
        timeTicker.tick(1)
        val actual = callback.text
        val expected = "6"
        assertEquals(expected, actual)
    }

    @Test
    fun test_stop_after_2_seconds() {
        val testDataSource = TestDataSource()
        val timeTicker = TestTimeTicker()
        val model = Model(testDataSource, timeTicker)
        val callback = TestCallback()
        testDataSource.saveInt("", 0)
        model.start(callback)
        timeTicker.tick(2)
        val actual = callback.text
        val expected = "2"
        assertEquals(expected, actual)

        model.stop()
        val savedCountActual = testDataSource.getInt("")
        val savedCountExpected = 2
        assertEquals(savedCountExpected, savedCountActual)
    }

}

private class TestCallback : TextCallback {
    var text = ""
    override fun updateText(str: String) {
        text = str
    }
}

private class TestDataSource : DataSource {
    private var int: Int = Int.MIN_VALUE
    override fun saveInt(key: String, value: Int) {
        int = value
    }

    override fun getInt(key: String): Int = int

    @Test
    fun test_start_wih_saved_value() {
        val testDataSource = TestDataSource()
        val model = Model(testDataSource, TestTimeTicker())
        val callback = TestCallback()
    }
}

private class TestTimeTicker : TimeTicker {

    private var callback: TimeTicker.Callback? = null
    var state = 0

    override fun start(callback: TimeTicker.Callback, period: Long) {
        this.callback = callback
        state = 1
    }

    override fun stop() {
        callback = null
        state = -1
    }

    fun tick(times: Int) {
        for (i in 0 until times)
            callback?.tick()
    }

}
