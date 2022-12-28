package com.example.stockmanager.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.room.Room
import com.example.stockmanager.R
import com.example.stockmanager.db_users.User
import com.example.stockmanager.db_users.UserRecord
import com.example.stockmanager.db_users.UsersDB
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sign_up_button.setOnClickListener {
            if (email.toString().isNotEmpty()) {
                ecrire()
                lire()
            }
        }
    }

    private fun ecrire() {
        val u = User(0, email.text.toString(), pass.text.toString())
        val db = Room.databaseBuilder(
            applicationContext, UsersDB::class.java, "UserTable"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        val dao = db.userDao()
        val u1 = UserRecord(0)
        dao.insertUser(u1)
        Toast.makeText(this, u.toString(), Toast.LENGTH_LONG).show()
    }

    private fun lire() {

        val db = Room.databaseBuilder(
            applicationContext,
            UsersDB::class.java, "UserTable"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        val dao = db.userDao()
        val liste = dao.getAllUsers()
        liste.forEach { item -> Log.i("READ", item.toString()) }
    }
}