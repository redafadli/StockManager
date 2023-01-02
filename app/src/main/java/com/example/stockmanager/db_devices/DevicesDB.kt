package com.example.stockmanager.db_devices

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ (DeviceRecord::class) ], version = 2)
abstract class DevicesDB : RoomDatabase(){

    abstract fun deviceDao(): DeviceDao
}