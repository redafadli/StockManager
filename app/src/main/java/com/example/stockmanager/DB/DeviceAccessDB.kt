package com.example.stockmanager.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DeviceAccessDB() {

    private val VERSION : Int = 1
    private val NAME_DB : String = "Device.db"

    private val TABLE_DEVICE : String = "TABLE_DEVICE"
    private val COL_TYPE : String = "TYPE"
    private val NUM_COL_TYPE : Int = 1
    private val COL_MARQUE_ET_MODELE : String = "MARQUE_ET_MODELE"
    private val NUM_COL_MARQUE_ET_MODELE : Int = 2
    private val COL_NUM_REF : String = "NUM_REF"
    private val NUM_COL_NUM_REF : Int = 3
    private val COL_SITE_WEB : String = "SITE_WEB"
    private val NUM_COL_SITE_WEB : Int = 4
    private val COL_QR_CODE : String = "QR_CODE"
    private val NUM_COL_QR_CODE : Int = 5

    private var db : SQLiteDatabase = TODO()
    private var devicedb : DevicesDB

    constructor(c:Context) : this() {
        devicedb = DevicesDB(c, NAME_DB, null, VERSION)
    }

    fun addDevice(device : Device) : Long{
        var content : ContentValues = ContentValues()
        content.put(COL_TYPE, device.type)
        content.put(COL_MARQUE_ET_MODELE, device.marque_et_modele)
        content.put(COL_NUM_REF, device.num_ref)
        content.put(COL_SITE_WEB, device.site_web)
        content.put(COL_QR_CODE, device.qr_code)

        return db.insert(TABLE_DEVICE, null, content)
    }

    fun editDevice(ref : String, deviceToUpdate: Device): Int {
        var content : ContentValues = ContentValues()
        content.put(COL_TYPE, deviceToUpdate.type)
        content.put(COL_MARQUE_ET_MODELE, deviceToUpdate.marque_et_modele)
        content.put(COL_NUM_REF, deviceToUpdate.num_ref)
        content.put(COL_SITE_WEB, deviceToUpdate.site_web)
        content.put(COL_QR_CODE, deviceToUpdate.qr_code)

        return db.update(TABLE_DEVICE, content, "$COL_NUM_REF = $ref", null)
    }

    fun deleteDevice(numref : String) : Int {
        return db.delete(TABLE_DEVICE, "$COL_NUM_REF = $numref", null)
    }

    fun openForWrite(){
        db = devicedb.writableDatabase
    }
    fun openForRead(){
        db = devicedb.readableDatabase
    }
    fun Close(){
        db.close()
    }
}