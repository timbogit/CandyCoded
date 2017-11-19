package com.timschmelmer.candycoded

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import android.widget.AdapterView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_view_title)
        textView.text = getString(R.string.products_title)

        val candyList = arrayListOf<String>("Oreos", "Cookies", "Lollipops", "Tropical Wave",
                "Berry Bouncer", "Grape Gummer", "Apple of My Eye", "Much Minty", "So Fresh",
                "Sassy Sandwich Cookie",
                "Uni-pop",
                "Strawberry Surprise",
                "Wish Upon a Star",
                "Planetary Pops",
                "Watermelon Like Whoa",
                "Twist 'n' Shout",
                "Beary Squad Goals",
                "ROYGBIV Spinner")
        val adapter = ArrayAdapter<String>(this,
                R.layout.list_view_candy,
                R.id.text_view_candy,
                candyList)
        val listView = findViewById<ListView>(R.id.list_view_candy)
        listView.adapter = adapter

        val context : Context = this
        val text = "Hello toast!"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, text, duration)
        toast.show()

        listView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
//                val newToast = Toast.makeText(this@MainActivity, "" + i, Toast.LENGTH_SHORT)
//                newToast.show()
                val detailIntent =
                        Intent(this@MainActivity, DetailActivity::class.java)
                detailIntent.putExtra("candy_name", candyList[i])
                startActivity(detailIntent)
            }
        }


    }
}
