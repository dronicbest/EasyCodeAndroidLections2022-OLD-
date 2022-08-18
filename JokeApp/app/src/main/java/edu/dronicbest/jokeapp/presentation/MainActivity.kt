package edu.dronicbest.jokeapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.dronicbest.jokeapp.JokeApp
import edu.dronicbest.jokeapp.R
import edu.dronicbest.jokeapp.presentation.custom_views.FavoriteDataView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = (application as JokeApp).viewModel
        val favoriteDataView = findViewById<FavoriteDataView>(R.id.jokeFavoriteDataView)

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