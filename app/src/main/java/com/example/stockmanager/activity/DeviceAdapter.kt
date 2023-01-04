package com.example.stockmanager.activity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.stockmanager.R
import com.example.stockmanager.db_devices.DeviceRecord
import com.example.stockmanager.db_devices.DevicesDB
import com.example.stockmanager.db_users.UsersDB
import kotlinx.android.synthetic.main.device_view_holder.view.*

class DeviceAdapter(private val allDevices: List<DeviceRecord>) :
    RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.device_view_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val db = Room.databaseBuilder(
            holder.itemView.context, UsersDB::class.java, "UserTable"
        ).allowMainThreadQueries().build()
        val dao = db.userDao()
        val sharedPreference = holder.itemView.context
            .getSharedPreferences("application", Context.MODE_PRIVATE)

        holder.itemView.device_type.text = allDevices[position].type
        holder.itemView.device_marque.text = allDevices[position].marque_modele
        holder.itemView.device_num_ref.text = allDevices[position].num_ref
        holder.itemView.device_site.text = allDevices[position].site_web
        holder.itemView.device_state.text = allDevices[position].state
        val id: Int = sharedPreference.getInt("id", 1)
        val currentUser = dao.getUser(id)
        if (!currentUser.rights) {
            holder.itemView.device_options.isVisible = false
        } else {

            holder.itemView.device_options.setOnClickListener { it ->
                val popup = PopupMenu(holder.itemView.context, it)
                popup.inflate(R.menu.device_menu)
                popup.show()
                popup.setOnMenuItemClickListener {
                    val db = Room.databaseBuilder(
                        holder.itemView.context, DevicesDB::class.java, "DeviceTable"
                    ).allowMainThreadQueries().build()

                    val d1 = allDevices[position]
                    val dao = db.deviceDao()

                    when (it.itemId) {
                        R.id.delete_device -> {
                            dao.deleteDevice(d1)
                        }
                        R.id.edit_device -> {
                            val editDeviceIntent =
                                Intent(holder.itemView.context, EditDevice::class.java)
                            editDeviceIntent.putExtra("id", d1.id)
                            editDeviceIntent.putExtra("type", d1.type)
                            editDeviceIntent.putExtra("device_marque", d1.marque_modele)
                            editDeviceIntent.putExtra("num_ref", d1.num_ref)
                            editDeviceIntent.putExtra("site_web", d1.site_web)
                            editDeviceIntent.putExtra("state", d1.state)
                            holder.itemView.context.startActivity(editDeviceIntent)
                        }
                    }
                    true
                }
            }
        }
    }

    override fun getItemCount(): Int = allDevices.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}