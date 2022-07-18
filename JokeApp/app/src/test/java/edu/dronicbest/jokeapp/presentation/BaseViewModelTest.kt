package edu.dronicbest.jokeapp.presentation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

/**
 * JokeApp
 *
 * @author dronicbest on 18.07.2022
 */
class BaseViewModelTest {
    @ExperimentalCoroutinesApi
    @Test
    fun test_get_joke_from_cloud_success(): Unit = runBlocking {
        val model = TestModel()
        val communication = TestCommunication()
        val viewModel = BaseViewModel(model, communication, TestCoroutineDispatcher())

        model.success = true
        viewModel.chooseFavorites(false)
        viewModel.getJoke()

        val actualText = communication.text
        val actualId = communication.id
        val expectedText = "cloudJokeText\ncloudJokePunchline"
        assertEquals(expectedText, actualText)
        assertNotEquals(0, actualId)
    }

    private inner class TestModel : Model {

        private val cacheJokeUiModel = BaseJokeUiModel("cachedJokeText", "cachedJokePunchline")
        private val cacheJokeFailure = FailedJokeUiModel("cacheFailed")
        private val cloudJokeUiModel = BaseJokeUiModel("cloudJokeText", "cloudJokePunchline")
        private val cloudJokeFailure = FailedJokeUiModel("cloudFailed")
        var success: Boolean = false
        private var getFromCache = false
        private var cachedJoke: JokeUiModel? = null

        override suspend fun getJoke(): JokeUiModel {
            TODO("Not yet implemented")
        }

        override suspend fun changeJokeStatus(): JokeUiModel? {
            TODO("Not yet implemented")
        }

        override fun chooseDataSource(cached: Boolean) {
            getFromCache = cached
        }

    }
}