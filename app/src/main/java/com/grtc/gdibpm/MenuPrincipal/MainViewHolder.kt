package com.grtc.gdibpm.MenuPrincipal

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.R

class MainViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_main , viewGroup, false)){

    private var imgDesplazamiento: ImageView? = null
    private var textDesplazamiento: TextView? = null

    init {
        imgDesplazamiento = itemView.findViewById(R.id.imgDesplazamiento)
        textDesplazamiento = itemView.findViewById(R.id.textDesplazamiento)
    }
    fun bind(main: Main){
        textDesplazamiento?.text= main.nombre
        imgDesplazamiento?.setImageResource(main.imagen)
    }

}