package com.example.stockmanager.db_users

import androidx.room.*


@Entity(tableName = "UserTable")
data class UserRecord(val login : String, val pwd : String, val rights : Boolean) {

    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Int=0

}