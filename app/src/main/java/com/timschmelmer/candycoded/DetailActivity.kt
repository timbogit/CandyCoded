package com.timschmelmer.candycoded

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = this@DetailActivity.intent
        var candyName = ""
        if (intent.hasExtra("candy_name")) {
            candyName = intent.getStringExtra("candy_name")
        }

        val textView = findViewById<TextView>(R.id.text_view_name)
        textView.text = candyName
    }
}
