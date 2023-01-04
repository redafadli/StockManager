package com.example.stockmanager.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.room.Room
import com.example.stockmanager.R
import com.example.stockmanager.db_devices.DevicesDB
import kotlinx.android.synthetic.main.activity_edit_device.*
import kotlinx.android.synthetic.main.activity_edit_device.add_marque_modele
import kotlinx.android.synthetic.main.activity_edit_device.add_num_ref
import kotlinx.android.synthetic.main.activity_edit_device.add_site_web

class EditDevice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val extras: Bundle? = intent.extras

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_device)

        val statusSpinner: Spinner = findViewById(R.id.edit_status_spinner)
        ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                statusSpinner.adapter = adapter
            }

        val typeSpinner: Spinner = findViewById(R.id.add_spinner_type)
        ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                typeSpinner.adapter = adapter
            }

        if (extras != null) {
            add_marque_modele.setText(extras.getString("device_marque"))
            add_num_ref.setText(extras.getString("num_ref"))
            add_site_web.setText(extras.getString("site_web"))

            edit_device_button.setOnClickListener {
                val db = Room.databaseBuilder(
                    applicationContext, DevicesDB::class.java, "DeviceTable"
                ).allowMainThreadQueries().build()
                val dao = db.deviceDao()
                dao.updateDevice(
                    typeSpinner.selectedItem.toString(),
                    add_marque_modele.text.toString(),
                    add_num_ref.text.toString(),
                    add_site_web.text.toString(),
                    statusSpinner.selectedItem.toString(),
                    extras.getInt("id")
                )
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}