package com.example.stockmanager.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.stockmanager.R
import com.example.stockmanager.db_users.UserRecord
import com.example.stockmanager.db_users.UsersDB
import kotlinx.android.synthetic.main.activity_log_in.*

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val db = Room.databaseBuilder(
            applicationContext, UsersDB::class.java, "UserTable"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

        val dao = db.userDao()
        val usersList = dao.getAllUsers()

        sign_up_button.setOnClickListener {
            val signUpIntent = Intent(this, SignUp::class.java)
            startActivity(signUpIntent)
        }

        log_in_button.setOnClickListener {

            if (email_log_in.text.toString().isEmpty()) {
                Toast.makeText(this, "Entrez votre email", Toast.LENGTH_LONG).show()
            } else if (pass_log_in.text.toString().isEmpty()) {
                Toast.makeText(this, "Entrez votre mot de pass", Toast.LENGTH_LONG).show()
            } else {
                for (user: UserRecord in usersList) {
                    if (email_log_in.text.toString() == user.login && pass_log_in.text.toString() == user.pwd) {
                        val sharedPreference =
                            getSharedPreferences("application", Context.MODE_PRIVATE)
                        val editor = sharedPreference.edit()
                        editor.putInt("id", user.id)
                        editor.apply()
                        val mainIntent = Intent(this, MainActivity::class.java)
                        startActivity(mainIntent)
                    } else {
                        Toast.makeText(this, "Informations incorrectes", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}