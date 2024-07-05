package com.grtc.gdibpm.displacement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentReference
import com.grtc.gdibpm.R

class DisplacementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val txtDate: TextView = itemView.findViewById(R.id.txtDate)
    private val txtMotive: TextView = itemView.findViewById(R.id.txtMotive)
    private val txtSender: TextView = itemView.findViewById(R.id.txtSender)
    private val txtReceiver: TextView = itemView.findViewById(R.id.txtReceiver)
    private val displacementHeritageRecyclerView: RecyclerView = itemView.findViewById(R.id.DisplacementHeritage)

    fun bind(displacement: Displacement, clickListener: (Displacement) -> Unit) {
        txtDate.text = displacement.date
        txtMotive.text = displacement.motive
        txtSender.text = displacement.senderName
        txtReceiver.text = displacement.receiverName

        displacementHeritageRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
        displacementHeritageRecyclerView.adapter = HeritagesAdapter(displacement.listHeritage.filterNotNull())
        displacementHeritageRecyclerView.visibility = if (displacement.isExpanded) View.VISIBLE else View.GONE

        itemView.setOnClickListener {
            clickListener(displacement)
        }
    }

    companion object {
        fun create(parent: ViewGroup): DisplacementViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_displacement, parent, false)
            return DisplacementViewHolder(view)
        }
    }
}
