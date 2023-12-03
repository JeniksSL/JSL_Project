package com.iba.jslproject.activity.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iba.jslproject.R
import com.iba.jslproject.data.User

class ContactsAdapter: RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    lateinit var users:List<User>

    fun addItems(items:List<User>){
        users = items
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int =users.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val user = users[position]
        holder.textName.text = user.name
        holder.textDetails.text = user.id
    }

    inner class ContactViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        lateinit var textName:TextView
        lateinit var textDetails: TextView
        init {
            textName = itemView.findViewById(R.id.tv_item_contact_name)
            textDetails = itemView.findViewById(R.id.tv_item_contact_details)
        }

    }
}