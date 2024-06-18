package com.rmldemo.guardsquare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rmldemo.guardsquare.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var activityDetailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate( layoutInflater )
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