package com.example.stockmanager.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room.databaseBuilder
import com.example.stockmanager.R
import com.example.stockmanager.db_devices.DevicesDB
import com.example.stockmanager.db_users.UsersDB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.device_view_holder.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.stockmanager.R.layout.activity_main)

        //load devices
        val devicesDatabase = databaseBuilder(
            applicationContext, DevicesDB::class.java,
            "DeviceTable"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()

        val allDevices = devicesDatabase.deviceDao().getAllDevices()

        recycler_devices.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = DeviceAdapter(allDevices)
        }

        val db = databaseBuilder(
            applicationContext, UsersDB::class.java, "UserTable"
        ).allowMainThreadQueries().build()
        val dao = db.userDao()

        val sharedPreference = getSharedPreferences("application", Context.MODE_PRIVATE)
        if (!sharedPreference.contains("id")) {
            val intentLogIn = Intent(this, LogIn::class.java)
            startActivity(intentLogIn)

        }
        //to check if he has right to edit
        else {
            val id: Int = sharedPreference.getInt("id", 1)
            val currentUser = dao.getUser(id)
            if (!currentUser.rights) {
                mainToolbar.menu.findItem(R.id.to_users_toolbar).isVisible = false
                mainToolbar.menu.findItem(R.id.add_device_toolbar).isVisible = false
            }
        }

        mainToolbar.setOnMenuItemClickListener {
            val addDeviceIntent = Intent(this, AddNewDevice::class.java)
            val toUsersIntent = Intent(this, Users::class.java)
            val logInIntent = Intent(this, LogIn::class.java)


            val sharedPreference = getSharedPreferences("application", Context.MODE_PRIVATE)

            when (it.itemId) {
                R.id.add_device_toolbar -> startActivity(addDeviceIntent)
                R.id.to_users_toolbar -> startActivity(toUsersIntent)
                R.id.log_out -> {
                    sharedPreference.edit().remove("id").apply()
                    startActivity(logInIntent)
                }
            }
            true
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(com.example.stockmanager.R.menu.toolbar_menu, menu)
//        return true
//    }
}