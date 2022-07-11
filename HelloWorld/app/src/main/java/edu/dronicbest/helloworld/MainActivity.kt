package edu.dronicbest.helloworld

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = (application as MyApplication).viewModel
        val textView: TextView = findViewById(R.id.textView)
        val observable = TextObservable()
        observable.observe(object : TextCallback {
            override fun updateText(str: String) = runOnUiThread {
                textView.text = str
            }
        })
        viewModel.init(observable)
    }

    override fun onResume() {
        super.onResume()
        viewModel.resumeCounting()
    }

    override fun onPause() {
        super.onPause()
        viewModel.pauseCounting()
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}