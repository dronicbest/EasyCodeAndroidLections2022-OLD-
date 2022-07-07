package edu.dronicbest.helloworld

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputEditText: TextInputEditText

    private val textWatcher: TextWatcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable?) {
            val input = s.toString()
            if (input.endsWith("@g")) {
                setText("${input}mail.com")
            }
        }
    }

    private fun setText(text: String) {
        textInputEditText.removeTextChangedListener(textWatcher)
        textInputEditText.setTextCorrectly(text)
        textInputEditText.addTextChangedListener(textWatcher)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)
        textInputEditText = textInputLayout.editText as TextInputEditText
        textInputEditText.addTextChangedListener(textWatcher)
        textInputEditText.listenerChanges { textInputLayout.isErrorEnabled = false }

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            if (Patterns.EMAIL_ADDRESS.matcher(textInputEditText.text.toString()).matches()) {
                Snackbar.make(loginButton, "Go to postLogin", Snackbar.LENGTH_LONG)
            } else {
                textInputLayout.isErrorEnabled = true
                textInputLayout.error = getString(R.string.invalid_email_address_msg)
            }
        }
    }

    fun TextInputEditText.listenerChanges(block: (test: String) -> Unit) {
        addTextChangedListener(object : SimpleTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                block.invoke(s.toString())
            }
        })
    }

    fun TextInputEditText.setTextCorrectly(text: CharSequence) {
        setText(text)
        setSelection(text.length)
    }
}