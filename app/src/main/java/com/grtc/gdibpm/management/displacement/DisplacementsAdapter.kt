package com.grtc.gdibpm.management.displacement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.grtc.gdibpm.R
import com.grtc.gdibpm.displacement.Displacement
import com.grtc.gdibpm.displacement.DisplacementStatus

class DisplacementsAdapter(
    private var list: MutableList<Displacement> = mutableListOf(),
    private val onUpdateClick: (Displacement) -> Unit,
    private val onDeleteClick: (Displacement) -> Unit
) : RecyclerView.Adapter<DisplacementsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplacementsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DisplacementsViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DisplacementsViewHolder, position: Int) {
        val displacement = list[position]
        holder.bind(displacement)

        holder.itemView.findViewById<MaterialButton>(R.id.btnUpdate).setOnClickListener {
            onUpdateClick.invoke(displacement)
        }

        holder.itemView.findViewById<MaterialButton>(R.id.btnDelete).setOnClickListener {
            onDeleteClick.invoke(displacement)
        }
    }

    fun setDisplacements(displacements: List<Displacement>) {
        list.clear()
        list.addAll(displacements)
        notifyDataSetChanged()
    }
}

