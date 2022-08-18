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
    private val checkBox: CheckBox
    private val textView: CorrectTextView
    private val changeButton: CorrectImageButton
    private val actionButton: CorrectButton
    private val process: CorrectProgressBar

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        orientation = VERTICAL
        (context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.favorite_data_view, this, true)

        checkBox = findViewById(R.id.checkBox)
        textView = findViewById(R.id.textView)
        changeButton = findViewById(R.id.changeButton)
        process = findViewById(R.id.progressBar)
        actionButton = findViewById(R.id.actionButton)
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

    fun show(state: State) = state.show(process, actionButton, textView, changeButton)
}