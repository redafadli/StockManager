package com.example.stockmanager.db_devices

import androidx.room.*


@Entity(tableName = "DeviceTable")
data class DeviceRecord(var type : String, var marque_modele : String, var num_ref : String,
                        var site_web: String, var qr_code : String) {

    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Int=0
}