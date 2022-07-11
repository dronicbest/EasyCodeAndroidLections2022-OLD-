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
        val model = Model(testDataSource)
        val callback = TestCallback()
        testDataSource.saveInt("", 5)
        model.start(callback)
        val actual = callback.text
        val expected = "5"
        assertEquals(expected, actual)
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
        val model = Model(testDataSource)
        val callback = TestCallback()
    }
}