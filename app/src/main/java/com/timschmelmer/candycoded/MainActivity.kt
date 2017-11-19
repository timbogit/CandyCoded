package com.timschmelmer.candycoded

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import android.widget.AdapterView
import com.google.gson.GsonBuilder

import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler

import cz.msebera.android.httpclient.Header


class MainActivity : AppCompatActivity() {
    private var candies: Array<Candy>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_view_title)
        textView.text = getString(R.string.products_title)

        val candyList = arrayListOf<String>("Oreos")
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
                val detailIntent =
                        Intent(this@MainActivity, DetailActivity::class.java)
                detailIntent.putExtra("candy_name", candies?.get(i)?.name)
                detailIntent.putExtra("candy_price", candies?.get(i)?.price)
                detailIntent.putExtra("candy_image", candies?.get(i)?.image)
                detailIntent.putExtra("candy_desc", candies?.get(i)?.description)
                startActivity(detailIntent)
            }
        }

        val client = AsyncHttpClient()
        val url = "https://s3.amazonaws.com/courseware.codeschool.com/super_sweet_android_time/API/CandyCoded.json"
        client.get(url,
                object : TextHttpResponseHandler() {
                    override fun onFailure(statusCode: Int, headers: Array<Header>, response: String, throwable: Throwable) {
                        Log.e("AsyncHttpClient", "response = " + response)
                    }

                    override fun onSuccess(statusCode: Int, headers: Array<Header>, response: String) {
                        Log.d("AsyncHttpClient", "response = " + response)
                        val gson = GsonBuilder().create()
                        candies = gson.fromJson(response, Array<Candy>::class.java)

                        adapter.clear()
                        candies?.forEach {  candy ->  adapter.add(candy.name) }
                    }
                })
    }
}
