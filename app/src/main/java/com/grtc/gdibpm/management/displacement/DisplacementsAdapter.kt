package com.grtc.gdibpm.management.displacement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.displacement.Displacement

class DisplacementsAdapter(private var list: List<Displacement> = emptyList()) : RecyclerView.Adapter<DisplacementsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplacementsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DisplacementsViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DisplacementsViewHolder, position: Int) {
        val displacement = list[position]
        holder.data(displacement)
    }

    fun setDisplacements(displacements: List<Displacement>) {
        list = displacements
        notifyDataSetChanged()
    }
}
