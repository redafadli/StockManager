package com.example.stockmanager.db_users

import androidx.room.*


@Entity(tableName = "UserTable")
data class UserRecord(val i : Int) {

    @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Int=0
    @ColumnInfo(name="login") var login : String = ""
    @ColumnInfo(name="pwd") var pwd: String = ""

}