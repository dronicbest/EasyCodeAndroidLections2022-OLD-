package edu.dronicbest.helloworld

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
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

        val contentLayout = findViewById<LinearLayout>(R.id.contentLayout)

        val processBar = findViewById<ProgressBar>(R.id.progressBar)

        val checkBox = findViewById<CheckBox>(R.id.checkBox)

        val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)
        textInputEditText = textInputLayout.editText as TextInputEditText
        textInputEditText.addTextChangedListener(textWatcher)
        textInputEditText.listenerChanges { textInputLayout.isErrorEnabled = false }

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            if (Patterns.EMAIL_ADDRESS.matcher(textInputEditText.text.toString()).matches()) {
                Snackbar.make(loginButton, "Go to postLogin", Snackbar.LENGTH_LONG)
                loginButton.isEnabled = false
                processBar.visibility = View.VISIBLE
                contentLayout.visibility = View.GONE
                hideKeyboard(textInputEditText)
                Handler(Looper.myLooper()!!).postDelayed({
                    contentLayout.visibility = View.VISIBLE
                    processBar.visibility = View.GONE
                    val dialog = BottomSheetDialog(this)
                    val view = LayoutInflater.from(this).inflate(R.layout.dialog, contentLayout, false)
                    dialog.setCancelable(false)
                    view.findViewById<View>(R.id.closeButton).setOnClickListener {
                        dialog.dismiss()
                    }
                    dialog.setContentView(view)
                    dialog.show()
                }, 3000)
            } else {
                textInputLayout.isErrorEnabled = true
                textInputLayout.error = getString(R.string.invalid_email_address_msg)
            }
        }

        checkBox.setOnCheckedChangeListener {_, isChecked ->
            loginButton.isEnabled = isChecked
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

    fun AppCompatActivity.hideKeyboard(view: View) {
        val imm = this.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
