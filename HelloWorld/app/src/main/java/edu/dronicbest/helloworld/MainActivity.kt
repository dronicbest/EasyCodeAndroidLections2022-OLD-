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
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private companion object {
        const val URL = "https://zavistnik.com/wp-content/uploads/2020/03/Android-kursy-zastavka.jpg"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image: ImageView = findViewById(R.id.iconImageView)
        image.setOnClickListener {
            image.setImageResource(R.mipmap.android_image)
        }
        val netImage = NetImage(URL, object : ImageCallback {
            override fun success(bitmap: Bitmap) {
                image.setImageBitmap(bitmap)
            }

            override fun failed() {
                Snackbar.make(image, "filed", Snackbar.LENGTH_SHORT).show()
            }

        })
        netImage.start()
    }
}