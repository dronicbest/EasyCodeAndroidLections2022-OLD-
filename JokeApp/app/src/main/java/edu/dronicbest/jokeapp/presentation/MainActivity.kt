package edu.dronicbest.jokeapp.presentation

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import edu.dronicbest.jokeapp.JokeApp
import edu.dronicbest.jokeapp.R
import edu.dronicbest.jokeapp.presentation.custom_views.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = (application as JokeApp).viewModel
        val favoriteDataView = findViewById<FavoriteDataView>(R.id.favoriteDataView)

        favoriteDataView.listenChanges { isChecked ->
            viewModel.chooseFavorites(isChecked)
        }

        favoriteDataView.handleChangeButton {
            viewModel.changeJokeStatus()
        }

        favoriteDataView.handleActionButton {
            viewModel.getJoke()
        }

        viewModel.observe(this) { state ->
            favoriteDataView.show(state)
        }
    }
}