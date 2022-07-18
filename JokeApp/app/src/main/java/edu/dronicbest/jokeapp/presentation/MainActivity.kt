package edu.dronicbest.jokeapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import edu.dronicbest.jokeapp.JokeApp
import edu.dronicbest.jokeapp.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as JokeApp).viewModel
        val button = findViewById<Button>(R.id.actionButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val textView = findViewById<TextView>(R.id.textView)

        progressBar.visibility = View.INVISIBLE

        button.setOnClickListener {
            button.isEnabled = false
            progressBar.visibility = View.VISIBLE
            viewModel.getJoke()
        }

        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.chooseFavorites(isChecked)
        }

        val changeButton = findViewById<ImageButton>(R.id.changeButton)
        changeButton.setOnClickListener {
            viewModel.changeJokeStatus()
        }

        viewModel.observe(this) { (text, drawableResId) ->
            button.isEnabled = true
            progressBar.visibility = View.INVISIBLE
            textView.text = text
            changeButton.setImageResource(drawableResId)
        }
    }

}