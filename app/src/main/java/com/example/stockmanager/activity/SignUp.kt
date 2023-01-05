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
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val sharedPreference =  getSharedPreferences("application", Context.MODE_PRIVATE)

        sign_up_button.setOnClickListener {
            if (pass_log_in.text.length <= 4) {
                Toast.makeText(
                    this,
                    "Veuillez entrez un mdp de plus de 4 caractères", Toast.LENGTH_LONG
                ).show()
            } else if (email_log_in.text.isEmpty()){
                Toast.makeText(
                    this,
                    "Veuillez entrez votre email", Toast.LENGTH_LONG
                ).show()
            } else {
                val logInIntent = Intent(this, LogIn::class.java)
                //check if the app is launched for the first time
                if(sharedPreference.getBoolean("first_time", true)){
                    addUserToDB(true)
                    sharedPreference.edit().putBoolean("first_time", false).apply()
                    startActivity(logInIntent)
                } else {
                    addUserToDB(false)
                    startActivity(logInIntent)
                }
            }
        }
    }
    private fun addUserToDB(rights : Boolean){
        val db = Room.databaseBuilder(
            applicationContext, UsersDB::class.java, "UserTable"
        ).allowMainThreadQueries().build()
        val dao = db.userDao()
        val u1 = UserRecord(email_log_in.text.toString(), pass_log_in.text.toString(), rights)
        dao.insertUser(u1)
    }
}