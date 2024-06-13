package com.grtc.gdibpm.heritage_asset

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.R

class HeritageViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_heritage_asset, viewGroup, false)){
    private var imgHeritage: ImageView? = null
    private var heritageCode: TextView? = null
    private var heritageState: TextView? = null

    init {
        imgHeritage = itemView.findViewById(R.id.HeritageCode)
        heritageCode = itemView.findViewById(R.id.HeritageEvidence)
        heritageState = itemView.findViewById(R.id.HeritageState)
    }
    fun bind(heritageAsset: HeritageAsset) {
        heritageCode?.text = heritageAsset.HeritageCode
        heritageState?.text = heritageAsset.HeritageState.toString()
        imgHeritage?.setImageResource(heritageAsset.HeritageEvidence)
    }
}