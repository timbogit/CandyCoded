package com.timschmelmer.candycoded

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = this@DetailActivity.intent
        val candyName = if (intent.hasExtra("candy_name")) intent.getStringExtra("candy_name") else ""
        val candyImage = if (intent.hasExtra("candy_image")) intent.getStringExtra("candy_image") else ""
        val candyPrice = if (intent.hasExtra("candy_price")) intent.getStringExtra("candy_price") else "0"
        val candyDesc = if (intent.hasExtra("candy_desc")) intent.getStringExtra("candy_desc") else ""

        val candyParts = mutableListOf<String>(candyName, candyPrice, candyImage, candyDesc)
        Log.d("DetailActivity", "Intent data: " + candyParts.joinToString(", "))

        val textViewName = findViewById<TextView>(R.id.text_view_name)
        textViewName.text = candyName
        val textViewPrice = findViewById<TextView>(R.id.text_view_price)
        textViewPrice.text = candyPrice
        val textViewDesc = findViewById<TextView>(R.id.text_view_desc)
        textViewDesc.text = candyDesc
        val imageView = findViewById<ImageView>(R.id.image_view_candy)
        Picasso.with(this).load(candyImage).into(imageView)

    }
}
