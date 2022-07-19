package edu.dronicbest.jokeapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import edu.dronicbest.jokeapp.JokeApp
import edu.dronicbest.jokeapp.R
import edu.dronicbest.jokeapp.presentation.custom_views.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: BaseViewModel

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as JokeApp).viewModel
        val button = findViewById<Button>(R.id.actionButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val textView = findViewById<CorrectTextView>(R.id.textView)

        progressBar.visibility = View.INVISIBLE

        button.setOnClickListener {
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

        viewModel.observe(this) { state ->
            state.show(
                object : ShowView {
                    override fun show(show: Boolean) {
                        progressBar.visibility = if (show) View.VISIBLE else View.INVISIBLE
                    }
                },
                object : EnableView {
                    override fun enable(enable: Boolean) {
                        button.isEnabled = enable
                    }
                },
                textView,
                object : ShowImage {
                    override fun show(id: Int) {
                        changeButton.setImageResource(id)
                    }
                }
            )
        }
    }

}