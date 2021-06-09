package com.example.apptrivia

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "HistoryDatabase"
        private val TABLE_CONTACTS = "HistoryTable"
        private val KEY_PLAYERNAME = "name"
        private val KEY_PLAYERANSWERONE = "answerOne"
        private val KEY_PLAYERANSWERTWO = "answerTwo"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.  
        //creating table with fields  
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_PLAYERNAME + " INTEGER PRIMARY KEY," + KEY_PLAYERANSWERONE + " TEXT,"
                + KEY_PLAYERANSWERTWO + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.  
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }


    //method to insert data  
    fun addHistory(his: HistoryModelClass): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_PLAYERNAME, his.name)
        contentValues.put(KEY_PLAYERANSWERONE, his.answerOne) // HistoryModelClass Answer
        contentValues.put(KEY_PLAYERANSWERTWO, his.answerTwo) // HistoryModelClass SecondAnswer
        // Inserting Row  
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack  
        db.close() // Closing database connection  
        return success
    }

    //method to read data  
    fun viewHistory(): List<HistoryModelClass> {
        val historyList: ArrayList<HistoryModelClass> = ArrayList<HistoryModelClass>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var playerName: String
        var playerAnswerOne: String
        var playerAnswerTwo: String
        if (cursor.moveToFirst()) {
            do {
                playerName = cursor.getString(cursor.getColumnIndex("name"))
                playerAnswerOne = cursor.getString(cursor.getColumnIndex("answerOne"))
                playerAnswerTwo = cursor.getString(cursor.getColumnIndex("answerTwo"))
                val emp = HistoryModelClass(name = playerName, answerOne = playerAnswerOne, answerTwo = playerAnswerTwo)
                historyList.add(emp)
            } while (cursor.moveToNext())
        }
        return historyList
    }
}