package com.grtc.gdibpm.management.displacement

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.grtc.gdibpm.R
import com.grtc.gdibpm.displacement.Displacement

class DisplacementsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_displacements, parent, false)) {

    private var txtMotive: TextView? = null
    private var txtSender: TextView? = null
    private var txtState: TextView? = null

    fun bind(displacement: Displacement) {
        txtMotive = itemView.findViewById(R.id.MotiveDisplacement)
        txtSender = itemView.findViewById(R.id.DisplacementSender)
        txtState = itemView.findViewById(R.id.StateDisplacement)

        txtMotive?.text = displacement.motive
        txtSender?.text = displacement.senderName
        txtState?.text = displacement.state.toString()
    }
}
