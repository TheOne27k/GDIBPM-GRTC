package com.grtc.gdibpm.displacement

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.R

//class DisplacementViewHolder (inflater: LayoutInflater, viewGroup: ViewGroup) :
//    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_displacement, viewGroup, false)){
//
//    private var TextRemitente: TextView? = null
//    private var TextAreaorigen: TextView? = null
//    private var TextContenido: TextView? = null
//    private var TextReceptor: TextView? = null
//    private var TextAreaDestino: TextView? = null
//
//    init {
//
//        TextRemitente = itemView.findViewById(R.id.TextRemitente)
//        TextAreaorigen = itemView.findViewById(R.id.TextAreaorigen)
//        TextContenido = itemView.findViewById(R.id.TextContenido)
//        TextReceptor = itemView.findViewById(R.id.TextReceptor)
//        TextAreaDestino = itemView.findViewById(R.id.TextAreaDestino)
//    }
//
//    fun bind(displacement: Displacement) {
//        TextAreaorigen?.text = displacement.area_origen
//        TextRemitente?.text = displacement.remitente
//        TextAreaDestino?.text = displacement.area_destino
//        TextContenido?.text = displacement.motivo
//        TextReceptor?.text = displacement.receptor
//
//    }
//
//
//}