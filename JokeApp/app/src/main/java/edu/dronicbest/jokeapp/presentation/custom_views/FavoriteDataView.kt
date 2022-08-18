package edu.dronicbest.jokeapp.presentation.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.LinearLayout
import edu.dronicbest.jokeapp.R
import edu.dronicbest.jokeapp.presentation.State

/**
 * JokeApp
 * @author dronicbest on 18.08.2022
 */
class FavoriteDataView : LinearLayout {
    private lateinit var checkBox: CheckBox
    private lateinit var textView: CorrectTextView
    private lateinit var changeButton: CorrectImageButton
    private lateinit var actionButton: CorrectButton
    private lateinit var progress: CorrectProgressBar

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet) {
        orientation = VERTICAL
        (context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.favorite_data_view, this, true)

        checkBox = findViewById(R.id.checkBox)
        textView = findViewById(R.id.textView)
        changeButton = findViewById(R.id.changeButton)
        progress = findViewById(R.id.progressBar)
        actionButton = findViewById(R.id.actionButton)

        context.theme.obtainStyledAttributes(attrs, R.styleable.FavoriteDataView, 0, 0).apply {
            try {
                val actionButtonText = getString(R.styleable.FavoriteDataView_actionButtonText)
                val checkBoxText = getString(R.styleable.FavoriteDataView_checkBoxText)
                checkBox.text = checkBoxText
                actionButton.text = actionButtonText
            } finally {
                recycle()
            }
        }
    }

    fun listenChanges(block: (checked: Boolean) -> Unit) =
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            block.invoke(isChecked)
        }

    fun handleChangeButton(block: () -> Unit) = changeButton.setOnClickListener {
        block.invoke()
    }

    fun handleActionButton(block: () -> Unit) = actionButton.setOnClickListener {
        block.invoke()
    }

    fun show(state: State) = state.show(progress, actionButton, textView, changeButton)
}