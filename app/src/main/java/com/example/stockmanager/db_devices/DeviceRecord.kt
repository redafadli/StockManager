package com.example.stockmanager.db_devices

import androidx.room.*


@Entity(tableName = "DeviceTable")
data class DeviceRecord(val i : Int) {

    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Int=0
    @ColumnInfo(name="type") var type : String = ""
    @ColumnInfo(name="marque_modele") var marque_modele : String = ""
    @ColumnInfo(name="num_ref") var num_ref : String = ""
    @ColumnInfo(name="site_web") var site_web: String = ""
    @ColumnInfo(name="qr_code") var qr_code : String = ""
}