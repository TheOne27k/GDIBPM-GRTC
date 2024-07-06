package com.grtc.gdibpm.management.area

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AreaAdapter(
    private val onDeleteClick: (Int) -> Unit // Cambiar a (Int) -> Unit
) : RecyclerView.Adapter<AreaViewHolder>() {

    private var list = mutableListOf<Area>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AreaViewHolder(inflater, parent, onDeleteClick)
    }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
        val area = list[position]
        holder.bind(area)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setArea(areas: List<Area>) {
        list.clear()
        list.addAll(areas)
        notifyDataSetChanged()
    }
    fun getItem(position: Int): Area {
        return list[position]
    }
}
