package com.example.stockmanager.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.stockmanager.R
import com.example.stockmanager.db_users.UsersDB
import kotlinx.android.synthetic.main.activity_users.*

class Users : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        val devicesDatabase = Room.databaseBuilder(applicationContext, UsersDB::class.java,
            "UserTable")
            .allowMainThreadQueries().build()

        val allUsers = devicesDatabase.userDao().getAllUsers()

        recycler_users.apply {
            layoutManager = LinearLayoutManager(this@Users)
            adapter = UserAdapter(allUsers)
        }
    }
}