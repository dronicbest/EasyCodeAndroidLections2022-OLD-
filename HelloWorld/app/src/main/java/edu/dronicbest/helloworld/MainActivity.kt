package edu.dronicbest.helloworld

import android.os.Bundle
import android.text.Editable
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputLayout: TextInputLayout
    private lateinit var textInputEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textInputLayout = findViewById(R.id.textInputLayout)
        textInputEditText = findViewById(R.id.textInputEditText)

        textInputEditText.addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                val validEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()
                textInputLayout.isErrorEnabled = !validEmail
                val error = if (validEmail) "" else getString(R.string.invalid_email_address_msg)
                textInputLayout.error = error
                if (validEmail) Toast.makeText(
                    this@MainActivity,
                    R.string.valid_email_address_msg,
                    Toast.LENGTH_LONG
                )
            }
        })
    }

    fun ImageView.loadFromURL(url: String) {
        Glide.with(this).load(url)
//            .fitCenter()
            .placeholder(android.R.drawable.ic_media_pause)
            .error(android.R.drawable.ic_dialog_alert)
            .into(this);
    }
}