package com.grtc.gdibpm.management.area

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AreaAdapter():RecyclerView.Adapter<AreaViewHolder>() {
    private var list = mutableListOf<Area>()
    fun setArea(area: List<Area>){
        list.clear()
        list.addAll(area)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AreaViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        val area = list[position]
        holder.data(area)
    }
}