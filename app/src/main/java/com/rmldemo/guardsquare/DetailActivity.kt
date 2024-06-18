package com.rmldemo.guardsquare

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.rmldemo.guardsquare.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var activityDetailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        activityDetailBinding = ActivityDetailBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(activityDetailBinding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.title = intent.getStringExtra("intent_title")

        Glide.with( this )
            .load(intent.getStringExtra("intent_image"))
            .placeholder(R.drawable.img_placeholder)
            .error(R.drawable.img_placeholder)
            .centerCrop()
            .into(activityDetailBinding.imageView)
    }
}