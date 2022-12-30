package com.example.stockmanager.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stockmanager.R
import com.example.stockmanager.db_devices.DeviceRecord
import kotlinx.android.synthetic.main.device_view_holder.view.*

class DeviceAdapter(private val allDevices: List<DeviceRecord>) : RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.device_view_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.device_type.text = allDevices[position].type
        holder.itemView.device_marque.text = allDevices[position].marque_modele
        holder.itemView.device_num_ref.text = allDevices[position].num_ref
        holder.itemView.device_site.text = allDevices[position].site_web
    }

    override fun getItemCount(): Int = allDevices.size

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

}