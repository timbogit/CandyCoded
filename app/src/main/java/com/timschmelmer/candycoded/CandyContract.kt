package com.timschmelmer.candycoded


import android.provider.BaseColumns

/**
 * Created by tim on 11/19/17.
 */
class CandyContract {
    companion object {
        val DB_NAME = "candycoded.db"
        val DB_VERSION = 1

        val SQL_CREATE_ENTRIES = "CREATE TABLE " + CandyEntry.TABLE_NAME + " (" +
                CandyEntry._ID + " INTEGER PRIMARY KEY," +
                CandyEntry.COLUMN_NAME_NAME + " TEXT," +
                CandyEntry.COLUMN_NAME_PRICE + " TEXT," +
                CandyEntry.COLUMN_NAME_DESC + " TEXT," +
                CandyEntry.COLUMN_NAME_IMAGE + " TEXT)"

        val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + CandyEntry.TABLE_NAME
    }


     class CandyEntry:BaseColumns {
        companion object {
            val _ID = "_id"
            val TABLE_NAME = "candy"
            val COLUMN_NAME_NAME = "name"
            val COLUMN_NAME_PRICE = "price"
            val COLUMN_NAME_DESC = "description"
            val COLUMN_NAME_IMAGE = "image"
        }
    }

}