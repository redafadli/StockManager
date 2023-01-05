package com.example.stockmanager.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.stockmanager.R
import com.example.stockmanager.db_devices.DeviceRecord
import com.example.stockmanager.db_devices.DevicesDB
import kotlinx.android.synthetic.main.activity_add_new_device.*

class AddNewDevice : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_device)

        val spinner: Spinner = findViewById(R.id.add_spinner_type)
        ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        add_device_button.setOnClickListener{
            if (add_marque_modele.text.isNotEmpty()) {
                val spinner: Spinner = findViewById(R.id.add_spinner_type)
                val type: String = spinner.selectedItem.toString()
                val db = Room.databaseBuilder(
                    applicationContext, DevicesDB::class.java, "DeviceTable"
                ).allowMainThreadQueries().build()
                val dao = db.deviceDao()
//                //qr code
//                val mMatrix: BitMatrix = MultiFormatWriter().encode(
//                    add_num_ref.text.toString(),
//                    BarcodeFormat.QR_CODE, 400, 400
//                )
                val d1 = DeviceRecord(
                    type, add_marque_modele.text.toString(), add_num_ref.text.toString(),
                    add_site_web.text.toString(), "Remise"
                )
                dao.insertDevice(d1)
                val mainIntent = Intent(this, MainActivity::class.java)
                startActivity(mainIntent)
            }
        }
    }
}