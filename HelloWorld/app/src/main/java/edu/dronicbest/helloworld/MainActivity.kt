package edu.dronicbest.helloworld

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private companion object {
//        const val URL = "https://zavistnik.com/wp-content/uploads/2020/03/Android-kursy-zastavka.jpg"
        const val URL = "https://images4.alphacoders.com/109/1095230.jpg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image: ImageView = findViewById(R.id.iconImageView)
        image.setOnClickListener {
            image.setImageResource(R.mipmap.android_image)
        }

        image.loadFromURL(URL)

    }

    fun ImageView.loadFromURL(url: String) {
        Glide.with(this).load(url)
//            .fitCenter()
            .placeholder(android.R.drawable.ic_media_pause)
            .error(android.R.drawable.ic_dialog_alert)
            .into(this);
    }
}