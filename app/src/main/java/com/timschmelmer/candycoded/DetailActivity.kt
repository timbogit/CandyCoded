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
        val position = if (intent.hasExtra("position")) intent.getIntExtra("position", 0) else 0
        val candyDbHelper = CandyDbHelper(this)
        val db = candyDbHelper.writableDatabase
        val cursor = db.rawQuery("SELECT * from " + CandyContract.CandyEntry.TABLE_NAME, null)
        cursor.moveToPosition(position)

        val candyName = cursor.getString(cursor.getColumnIndexOrThrow(CandyContract.CandyEntry.COLUMN_NAME_NAME))
        val candyImage = cursor.getString(cursor.getColumnIndexOrThrow(CandyContract.CandyEntry.COLUMN_NAME_IMAGE))
        val candyPrice = cursor.getString(cursor.getColumnIndexOrThrow(CandyContract.CandyEntry.COLUMN_NAME_PRICE))
        val candyDesc = cursor.getString(cursor.getColumnIndexOrThrow(CandyContract.CandyEntry.COLUMN_NAME_DESC))

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
