package com.grtc.gdibpm.displacement

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.R

class DisplacementViewHolder (inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_displacement, viewGroup, false)){

    private var txtSender: TextView? = null
    private var txtReceiver: TextView? = null
    private var txtMotive: TextView? = null
    private var txtDate: TextView? = null

    init {

        txtSender = itemView.findViewById(R.id.txtSender)
        txtReceiver = itemView.findViewById(R.id.txtReceiver)
        txtMotive = itemView.findViewById(R.id.txtMotive)
        txtDate = itemView.findViewById(R.id.txtDate)
    }

    fun data(displacement: Displacement) {
        txtSender?.text = displacement.sender.toString()
        txtReceiver?.text = displacement.receiver.toString()
        txtMotive?.text = displacement.motive
        txtDate?.text = displacement.date

    }


}