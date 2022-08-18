package edu.dronicbest.jokeapp.presentation

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import edu.dronicbest.jokeapp.JokeApp
import edu.dronicbest.jokeapp.R
import edu.dronicbest.jokeapp.presentation.custom_views.CorrectButton
import edu.dronicbest.jokeapp.presentation.custom_views.CorrectImageButton
import edu.dronicbest.jokeapp.presentation.custom_views.CorrectProgressBar
import edu.dronicbest.jokeapp.presentation.custom_views.CorrectTextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = (application as JokeApp).viewModel
        val button = findViewById<CorrectButton>(R.id.actionButton)
        val progressBar = findViewById<CorrectProgressBar>(R.id.progressBar)
        val textView = findViewById<CorrectTextView>(R.id.textView)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val changeButton = findViewById<CorrectImageButton>(R.id.changeButton)

        progressBar.visibility = View.INVISIBLE

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.chooseFavorites(isChecked)
        }

        changeButton.setOnClickListener {
            viewModel.changeJokeStatus()
        }

        button.setOnClickListener {
            viewModel.getJoke()
        }

        viewModel.observe(this) { state ->
            state.show(
                progressBar,
                button,
                textView,
                changeButton
            )
        }
    }
}