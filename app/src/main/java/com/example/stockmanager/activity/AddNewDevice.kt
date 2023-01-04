package com.example.stockmanager.activity

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
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
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
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                val dao = db.deviceDao()
                val d1 = DeviceRecord(
                    type, add_marque_modele.text.toString(), add_num_ref.text.toString(),
                    add_site_web.text.toString(), add_qr_code.text.toString(), "Remise"
                )
                dao.insertDevice(d1)
//                val mainIntent = Intent(this, MainActivity::class.java)
//                startActivity(mainIntent)
                Toast.makeText(this, d1.toString(), Toast.LENGTH_LONG).show()

                //  QR COOOOOODE ------------------------------->>>>>>>>>>>>>>>>>>
                //initializing MultiFormatWriter for QR code
                val mWriter = MultiFormatWriter();

                val mMatrix: BitMatrix = mWriter.encode(
                    add_num_ref.text.toString(),
                    BarcodeFormat.QR_CODE, 400, 400
                )


                val mEncoder = BarcodeEncoder()
                val mBitmap: Bitmap = mEncoder.createBitmap(mMatrix)
                //creating bitmap of code
                imageCode.setImageBitmap(mBitmap)
                //Setting generated QR code to imageView
            }
        }
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