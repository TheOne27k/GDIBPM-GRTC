package com.grtc.gdibpm.heritage_asset

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.grtc.gdibpm.R

class HeritageViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_heritage_asset, viewGroup, false)) {
    private var imgHeritage: ImageView = itemView.findViewById(R.id.heritage_evidence)
    private var heritageCode: TextView = itemView.findViewById(R.id.heritage_code)
    private var heritageState: TextView = itemView.findViewById(R.id.heritage_state)
    private var btnDelete: MaterialButton = itemView.findViewById(R.id.btn_delete)

    fun data(heritageAsset: HeritageAsset, onDelete: (Int) -> Unit) {
        heritageCode.text = heritageAsset.HeritageCode
        heritageState.text = heritageAsset.HeritageState.toString()
        Glide.with(itemView.context)
            .load(heritageAsset.HeritageEvidence)
            .into(imgHeritage)

        btnDelete.setOnClickListener {
            onDelete(adapterPosition)
        }
    }
}
