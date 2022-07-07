package edu.dronicbest.helloworld

import android.graphics.BitmapFactory
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.lang.Exception
import java.net.URL

class NetImage(
    private val url: String,
    private val callback: ImageCallback
) : Thread() {

    override fun run() {
        super.run()
        try {
            val connection = URL(url).openConnection()
            connection.doInput = true
            connection.connect()
            connection.getInputStream().use {
                callback.success(BitmapFactory.decodeStream(it))
            }
        } catch(e: Exception) {
            callback.failed()
        }
    }

    fun ImageView.loadFromURL(url: String) {
        Glide.with(this).load(url)
//            .fitCenter()
            .placeholder(android.R.drawable.ic_media_pause)
            .error(android.R.drawable.ic_dialog_alert)
            .into(this);
    }
}