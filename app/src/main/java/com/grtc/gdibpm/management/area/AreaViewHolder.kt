package com.grtc.gdibpm.management.area

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.grtc.gdibpm.R

class AreaViewHolder(
    inflater: LayoutInflater,
    viewGroup: ViewGroup,
    private val onDeleteClick: (Int) -> Unit // Cambiar a (Int) -> Unit
) : RecyclerView.ViewHolder(inflater.inflate(R.layout.item_area, viewGroup, false)) {

    private var txtName: TextView? = null

    init {
        txtName = itemView.findViewById(R.id.NameArea)

        // Configurar clic en el botón de eliminar
        itemView.findViewById<MaterialButton>(R.id.btnDelete).setOnClickListener {
            onDeleteClick(adapterPosition)
        }
    }

    fun bind(area: Area) {
        txtName?.text = area.name
    }
}
