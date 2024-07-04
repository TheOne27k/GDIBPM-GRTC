package com.grtc.gdibpm.management.user

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.R

class UserViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_user, viewGroup, false)){
        private var txtName: TextView? = null
        private var txtLastName: TextView? = null
        private var txtArea: TextView? = null
    init {
        txtName = itemView.findViewById(R.id.NameUser)
        txtLastName = itemView.findViewById(R.id.LastNameUser)
        txtArea = itemView.findViewById(R.id.AreaUser)
    }
    fun data(user: User){
        txtName?.text = user.name
        txtLastName?.text = user.lastname
        txtArea?.text = user.areaNane
    }
}