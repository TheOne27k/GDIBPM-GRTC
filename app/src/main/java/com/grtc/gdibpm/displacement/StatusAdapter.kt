package com.grtc.gdibpm.displacement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.R

class StatusAdapter(private val statusList: List<DisplacementStatus>) : RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_state, parent, false)
        return StatusViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        val currentItem = statusList[position]
        holder.statusTextView.text = currentItem.displayName
    }

    override fun getItemCount() = statusList.size

    class StatusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val statusTextView: TextView = itemView.findViewById(R.id.txtStatus)
    }
}