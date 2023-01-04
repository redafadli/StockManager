package com.example.stockmanager.db_users

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM UserTable")
    fun getAllUsers(): List<UserRecord>

    @Query("SELECT * FROM UserTable WHERE id = :id")
    fun getUser(id: Int): UserRecord

    @Insert
    fun insertUser(vararg listCategories: UserRecord)

    @Query("UPDATE UserTable SET login= :login, pwd= :pwd, rights= :rights WHERE id LIKE :id")
    fun updateUserRights(login : String, pwd: String, rights: Boolean, id : Int)

    @Delete
    fun deleteUser(task: UserRecord)
}