package edu.dronicbest.jokeapp.presentation.custom_views

import android.content.Context
import android.util.AttributeSet

/**
 * JokeApp
 * @author dronicbest on 19.07.2022
 */
class CorrectTextView : androidx.appcompat.widget.AppCompatTextView, ShowText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun show(text: String) {
        setText(text)
    }
}