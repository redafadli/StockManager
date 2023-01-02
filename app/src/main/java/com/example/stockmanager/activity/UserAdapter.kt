package com.example.stockmanager.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stockmanager.R
import com.example.stockmanager.db_users.UserRecord
import kotlinx.android.synthetic.main.user_view_holder.view.*

class UserAdapter(private val allUsers : List<UserRecord>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_view_holder, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.user_email.text = allUsers[position].login
    }

    override fun getItemCount(): Int = allUsers.size

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

}