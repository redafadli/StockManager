package com.example.stockmanager.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.stockmanager.R
import com.example.stockmanager.db_devices.DeviceRecord
import com.example.stockmanager.db_devices.DevicesDB
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val devicesDatabase = Room.databaseBuilder(applicationContext, DevicesDB::class.java,
            "DeviceTable")
            .allowMainThreadQueries().build()

        add_device.setOnClickListener {
            val intent = Intent(this, AddNewDevice::class.java)
            startActivity(intent)
        }

        add_user.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        devicesDatabase.deviceDao().insertDevice(DeviceRecord("Tablette", "test", "test", "test"
        ,"test"))
        val allDevices = devicesDatabase.deviceDao().getAllDevices()


        recycler_devices.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = DeviceAdapter(allDevices)
        }
    }
}