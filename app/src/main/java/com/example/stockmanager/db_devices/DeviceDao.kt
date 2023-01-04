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

    @Query("UPDATE DeviceTable SET type= :type, marque_modele= :marque_modele" +
            ", num_ref= :num_ref, site_web= :site_web, state= :state WHERE id LIKE :id")
    fun updateDevice(type : String, marque_modele : String, num_ref : String,
                      site_web: String, state : String, id : Int)

    @Delete
    fun deleteDevice(task: DeviceRecord)
}