package com.example.stockmanager.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ (UserRecord::class) ], version = 2)
abstract class UsersDB : RoomDatabase(){

    abstract fun userDao(): UserDao
}