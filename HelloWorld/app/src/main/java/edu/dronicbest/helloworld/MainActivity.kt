package edu.dronicbest.helloworld

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

const val TAG = "MyLog"
const val KEY= "screenState"

class MainActivity : AppCompatActivity() {

    private companion object {
        const val INITIAL = 0
        const val PROGRESS = 1
        const val SUCCESS = 2
        const val FAILED = 3
    }

    private var state = INITIAL

    private lateinit var textInputEditText: TextInputEditText
    private lateinit var textInputLayout: TextInputLayout

    private val textWatcher: TextWatcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable?) {
            Log.d(TAG, "changed ${s.toString()}")
            textInputLayout.isErrorEnabled = false
        }
    }

    override fun onPause() {
        super.onPause()
        textInputEditText.removeTextChangedListener(textWatcher)
    }

    override fun onResume() {
        super.onResume()
        textInputEditText.addTextChangedListener(textWatcher)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate ${savedInstanceState == null}")
        savedInstanceState?.let {
            state = it.getInt(KEY)
        }

        val contentLayout = findViewById<LinearLayout>(R.id.contentLayout)
        val processBar = findViewById<ProgressBar>(R.id.progressBar)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)

        textInputLayout = findViewById(R.id.textInputLayout)
        textInputEditText = textInputLayout.editText as TextInputEditText
        textInputEditText.addTextChangedListener(textWatcher)
        textInputEditText.listenerChanges {
            textInputLayout.isErrorEnabled = false
            Log.d(TAG, "changed $it")
        }

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            if (EMAIL_ADDRESS.matcher(textInputEditText.text.toString()).matches()) {
                hideKeyboard(textInputEditText)
                contentLayout.visibility = View.GONE
                processBar.visibility = View.VISIBLE
                state = PROGRESS
                Handler(Looper.myLooper()!!).postDelayed({
                    state = FAILED
                    contentLayout.visibility = View.VISIBLE
                    processBar.visibility = View.GONE
                    showDialog(contentLayout)
                }, 3000)
            } else {
                textInputLayout.isErrorEnabled = true
                textInputLayout.error = getString(R.string.invalid_email_address_msg)
            }

        }

        checkBox.setOnCheckedChangeListener {_, isChecked ->
            loginButton.isEnabled = isChecked
        }

        when (state) {
            FAILED -> showDialog(contentLayout)
            SUCCESS -> {
                state = INITIAL
                Snackbar.make(contentLayout, "Success", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun showDialog(viewGroup: ViewGroup) {
        val dialog = Dialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.dialog, viewGroup, false)
        dialog.setCancelable(false)
        view.findViewById<View>(R.id.closeButton).setOnClickListener {
            state = INITIAL
            dialog.dismiss()
        }
        dialog.setContentView(view)
        dialog.show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY, state)
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
