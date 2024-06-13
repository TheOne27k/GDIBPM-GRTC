package com.grtc.gdibpm.heritage_asset

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Heritagedapter (val list: List<HeritageAsset>) :
    RecyclerView.Adapter<HeritageViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeritageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HeritageViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HeritageViewHolder, position: Int) {
        val heritageAsset = list[position]
        holder.bind(heritageAsset)
    }

}