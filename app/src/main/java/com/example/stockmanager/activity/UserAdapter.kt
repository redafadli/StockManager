package com.example.stockmanager.activity

import android.app.ProgressDialog.show
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.stockmanager.R
import com.example.stockmanager.db_users.UserRecord
import com.example.stockmanager.db_users.UsersDB
import kotlinx.android.synthetic.main.device_view_holder.view.*
import kotlinx.android.synthetic.main.user_view_holder.view.*

class UserAdapter(private val allUsers: List<UserRecord>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_view_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val db = Room.databaseBuilder(
            holder.itemView.context, UsersDB::class.java, "UserTable"
        ).allowMainThreadQueries().build()
        val dao = db.userDao()

        holder.itemView.user_email.text = allUsers[position].login
        holder.itemView.menu_button.setOnClickListener { it ->
            val popup = PopupMenu(holder.itemView.context, it)
            popup.inflate(R.menu.user_menu)
            popup.show()
            popup.setOnMenuItemClickListener {
                val u1 = allUsers[position]
                when (it.itemId) {
                    R.id.add_right -> {
                        dao.updateUserRights(u1.login, u1.pwd, true, u1.id)
                        Toast.makeText(
                            holder.itemView.context, dao.getUser(u1.id).login + "a les drois : "
                                    + dao.getUser(u1.id).rights.toString(), Toast.LENGTH_LONG
                        ).show()
                        true
                    }

                    R.id.remove_right -> {
                        dao.updateUserRights(u1.login, u1.pwd, false, u1.id)
                        Toast.makeText(
                            holder.itemView.context, dao.getUser(u1.id).login + "a les drois : "
                                    + dao.getUser(u1.id).rights.toString(), Toast.LENGTH_LONG
                        ).show()
                        true
                    }

                    R.id.delete_user -> {
                        dao.deleteUser(u1)
                        true
                    }

                    else -> {
                        true
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = allUsers.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}