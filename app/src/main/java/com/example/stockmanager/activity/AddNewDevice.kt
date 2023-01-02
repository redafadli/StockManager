package com.example.stockmanager.activity

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.stockmanager.R
import com.example.stockmanager.db_devices.Device
import com.example.stockmanager.db_devices.DeviceRecord
import com.example.stockmanager.db_devices.DevicesDB
import kotlinx.android.synthetic.main.activity_add_new_device.*

class AddNewDevice : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_device)

        val spinner: Spinner = findViewById(R.id.edit_spinner_type)
        ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        add_device_button.setOnClickListener{
            if (edit_marque_modele.text.isNotEmpty()) {
                ecrire()
                lire()
            }
        }
    }

    private fun ecrire() {
        val spinner: Spinner = findViewById(R.id.edit_spinner_type)
        val type : String = spinner.selectedItem.toString()
        val device = Device(0, type, edit_marque_modele.text.toString(), edit_num_ref.text.toString(),
            edit_site_web.text.toString(), edit_qr_code.text.toString(), "Remise")
        val db = Room.databaseBuilder(
            applicationContext, DevicesDB::class.java, "DeviceTable"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        val dao = db.deviceDao()
        val d1 = DeviceRecord(device.type, device.marque_modele, device.num_ref, device.site_web,
            device.qr_code)
        dao.insertDevice(d1)
        Toast.makeText(this, device.toString(), Toast.LENGTH_LONG).show()
    }

    private fun lire() {
        val db = Room.databaseBuilder(
            applicationContext,
            DevicesDB::class.java, "DeviceTable"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        val dao = db.deviceDao()
        val liste = dao.getAllDevices()
        liste.forEach { item -> Log.i("READ", item.toString()) }
    }

}