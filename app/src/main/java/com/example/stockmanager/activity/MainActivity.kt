package com.example.stockmanager.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.stockmanager.R
import com.example.stockmanager.db_users.User
import com.example.stockmanager.db_users.UsersDB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.load_user

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_device.setOnClickListener {
            val intent = Intent(this, AddNewDevice::class.java)
            startActivity(intent)
        }

        add_user.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        load_user.setOnClickListener {
            lireCeLogin("test")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun lireCeLogin(l: String) {
        val db = Room.databaseBuilder(applicationContext, UsersDB::class.java, "UserTable").allowMainThreadQueries().build()
        val dao = db.userDao()
        val dbL = dao.getUser(l)
        val uL = User(dbL.id, dbL.login ?:"INDEFINI", dbL.pwd?:"INDEFINI")

        login_info.setTextColor(Color.RED)
        pwd_info.setTextColor(Color.RED)

        login_info.text = "LOGIN : " + uL.login
        pwd_info.text = "PASSWORD : " + uL.pwd
    }

}