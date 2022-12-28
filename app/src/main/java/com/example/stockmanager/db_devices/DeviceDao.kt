package com.example.stockmanager.db_devices

import androidx.room.*

@Dao
interface DeviceDao {

    @Query("SELECT * FROM DeviceTable")
    fun getAllDevices(): List<DeviceRecord>

    @Query("SELECT * FROM DeviceTable WHERE num_ref = :ref")
    fun getDevice(ref: String): DeviceRecord

    @Insert
    fun insertDevice(vararg listCategories: DeviceRecord)

    @Update
    fun updateDevice(task: DeviceRecord)

    @Delete
    fun deleteDevice(task: DeviceRecord)
}