package com.grtc.gdibpm.displacement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.R

class DisplacementAdapter(private var displacements: List<Displacement>) : RecyclerView.Adapter<DisplacementViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplacementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_displacement, parent, false)
        return DisplacementViewHolder(view)
    }

    override fun onBindViewHolder(holder: DisplacementViewHolder, position: Int) {
        val displacement = displacements[position]
        holder.bind(displacement) {
            displacement.isExpanded = !displacement.isExpanded
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = displacements.size

    fun updateDisplacements(newDisplacements: List<Displacement>) {
        displacements = newDisplacements
        notifyDataSetChanged()
    }
}
