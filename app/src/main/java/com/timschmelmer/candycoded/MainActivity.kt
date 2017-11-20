package com.timschmelmer.candycoded

import android.content.ContentValues
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
import com.timschmelmer.candycoded.CandyContract.CandyEntry





class MainActivity : AppCompatActivity() {
    private var candies: Array<Candy>? = null
    private val candyDbHelper = CandyDbHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_view_title)
        textView.text = getString(R.string.products_title)

        val db = candyDbHelper.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM " + CandyEntry.TABLE_NAME, null)

        val adapter = CandyCursorAdapter(this@MainActivity, cursor)

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
                detailIntent.putExtra("position", i)
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

                        addCandiesToDatabase(candies)

                        val db = candyDbHelper.writableDatabase
                        val cursor = db.rawQuery("SELECT * FROM " + CandyEntry.TABLE_NAME, null)
                        adapter.changeCursor(cursor)
                    }
                })
    }

    private fun addCandiesToDatabase(candies: Array<Candy>?) {
        val db = candyDbHelper.writableDatabase

        if (candies == null)
            return
        else
            db.delete(CandyEntry.TABLE_NAME, null, null)

        candies?.forEach {  candy ->
            val values = ContentValues()
            values.put(CandyEntry.COLUMN_NAME_NAME, candy.name)
            values.put(CandyEntry.COLUMN_NAME_PRICE, candy.price)
            values.put(CandyEntry.COLUMN_NAME_DESC, candy.description)
            values.put(CandyEntry.COLUMN_NAME_IMAGE, candy.image)

            db.insert(CandyEntry.TABLE_NAME, null, values)
        }
    }
}
