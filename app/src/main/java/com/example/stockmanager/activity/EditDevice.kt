package com.example.stockmanager.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.stockmanager.R
import com.example.stockmanager.db_devices.DevicesDB
import kotlinx.android.synthetic.main.activity_edit_device.*

class EditDevice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val extras: Bundle? = intent.extras

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_device)

        if (extras != null) {
            edit_marque_modele.setText(extras.getString("device_marque"))
            edit_num_ref.setText(extras.getString("num_ref"))
            edit_site_web.setText(extras.getString("site_web"))
            edit_qr_code.setText(extras.getString("qr_code"))
        }

        edit_device_button.setOnClickListener {
            val db = Room.databaseBuilder(
                applicationContext, DevicesDB::class.java, "DeviceTable"
            ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

            val dao = db.deviceDao()
            val numRef = extras?.getString("num_ref")
            if (numRef != null) {
                val deviceRecord = dao.getDevice(numRef)
            }
        }

    }
}