package com.grtc.gdibpm.management.area

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.R

class AreaViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_area, viewGroup, false)){
        private var txtName: TextView? = null
    init {
        txtName = itemView.findViewById(R.id.NameArea)
    }
    fun data(area: Area){
        txtName?.text = area.name
    }
}