package com.example.stockmanager.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DevicesDB(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, name, factory, version) {

    private val TABLE_DEVICE : String = "TABLE_DEVICE"
    private val COL_TYPE : String = "TYPE"
    private val COL_MARQUE_ET_MODELE : String = "MARQUE_ET_MODELE"
    private val COL_NUM_REF : String = "NUM_REF"
    private val COL_SITE_WEB : String = "SITE_WEB"
    private val COL_QR_CODE : String = "QR_CODE"

    private val CREATE_DB : String = "CREATE TABLE" + TABLE_DEVICE + " ("+
            COL_NUM_REF + " INTEGER PRIMARY KEY, " +
            COL_TYPE + " TEXT NOT NULL, " + COL_MARQUE_ET_MODELE + " TEXT NOT NULL, " +
            COL_SITE_WEB + " TEXT NOT NULL, " + COL_QR_CODE + " TEXT NOT NULL);"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_DB);
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE $TABLE_DEVICE");
        onCreate(db);
    }
}