package edu.dronicbest.jokeapp.presentation

/**
 * JokeApp
 * @author dronicbest on 17.08.2022
 */
class FailedJokeUiModel(private val text: String) : JokeUiModel(text, "") {
    override fun text() = text
    override fun getIconResId(): Int = 0
    override fun show(communication: Communication) = communication.showState(
        State.Failed(text(), getIconResId())
    )
}