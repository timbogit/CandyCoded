package com.timschmelmer.candycoded

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView

/**
 * Created by tim on 11/19/17.
 */
class CandyCursorAdapter(context: Context, cursor: Cursor):CursorAdapter(context, cursor, false) {
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.list_view_candy, parent, false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val textView = view?.findViewById<TextView>(R.id.text_view_candy)
        val candyName = cursor?.getString(cursor.getColumnIndexOrThrow("name"))
        textView?.text = candyName
    }
}