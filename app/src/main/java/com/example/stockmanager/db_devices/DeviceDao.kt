package com.example.stockmanager.db_users

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM UserTable")
    fun getAllUsers(): List<UserRecord>

    @Query("SELECT * FROM UserTable WHERE login = :login")
    fun getUser(login: String): UserRecord

    @Insert
    fun insertUser(vararg listCategories: UserRecord)

    @Update
    fun updateDevice(task: UserRecord)

    @Delete
    fun deleteDevice(task: UserRecord)
}