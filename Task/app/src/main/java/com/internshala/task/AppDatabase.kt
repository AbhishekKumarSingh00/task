package com.internshala.task

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {

        val DATABASE_NAME = "App.db"
        val TABLE_NAME = "AppDetails_table"
        val COL_ID = "ID"
        val COL_NAME = "NAME"
        //val COL_POPULAR = ""
        val COL_TYPE = "TYPE"
        val COL_COUNTRY = "COUNTRY"
    }


    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE IF NOT EXISTS $TABLE_NAME(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME  TEXT , COUNTRY STRING, TYPE STRING)") //Add popular if you want to

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

    }


    fun intertData(name: String, country: String, type: String): Boolean? {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME, name)
        //cv.put(COL_POPULAR, popular)
        cv.put(COL_TYPE, type)
        cv.put(COL_COUNTRY, country)
        val res = db.insert(TABLE_NAME , null, cv)
        return !res.equals(-1)
    }


    fun getAllData(): Cursor {

        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }


    fun getData(id: String): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME WHERE ID=? ", arrayOf(id), null)
    }

    fun updateData(id: String, name: String, country: String, type: String): Boolean? {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_ID, id)
        cv.put(COL_NAME, name)
       // cv.put(COL_POPULAR, popular)
        cv.put(COL_TYPE, type)
        cv.put(COL_COUNTRY, country)
        db.update(TABLE_NAME, cv, "ID=?", arrayOf(id))
        return true
    }

    fun daleteData(id: String): Int? {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "ID =? ", arrayOf(id))
    }
}