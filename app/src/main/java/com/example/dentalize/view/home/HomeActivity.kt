package com.example.dentalize.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.dentalize.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val gifUrl = "https://hips.hearstapps.com/hmg-prod/images/giphy-36-1503998280.gif?crop=1.00xw:0.753xh;0,0.129xh&resize=980:*"
        val gifImageView = findViewById<ImageView>(R.id.gifImageView)
        Glide.with(this)
            .asGif()
            .load(gifUrl)
            .into(gifImageView)
    }
}