package com.grtc.gdibpm.management.displacement

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.R
import com.grtc.gdibpm.displacement.Displacement

class DisplacementsViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_displacements, viewGroup, false)) {
    private var txtMotive: TextView? = null
    private var txtSender: TextView? = null
    private var txtState: TextView? = null

    init {
        txtMotive = itemView.findViewById(R.id.MotiveDisplacement)
        txtSender = itemView.findViewById(R.id.DisplacementSender)
        txtState = itemView.findViewById(R.id.StateDisplacement)
    }

    fun data(displacement: Displacement) {
        txtMotive?.text = displacement.motive
        txtSender?.text = displacement.senderName
        txtState?.text = displacement.state.toString()
    }
}
