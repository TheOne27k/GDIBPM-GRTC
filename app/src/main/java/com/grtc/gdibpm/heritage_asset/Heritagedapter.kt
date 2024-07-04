package com.grtc.gdibpm.heritage_asset

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HeritageAdapter(private val heritageList: MutableList<HeritageAsset>) :
    RecyclerView.Adapter<HeritageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeritageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HeritageViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: HeritageViewHolder, position: Int) {
        holder.data(heritageList[position])
    }

    override fun getItemCount(): Int = heritageList.size

    fun addItem(heritageAsset: HeritageAsset) {
        heritageList.add(heritageAsset)
        notifyItemInserted(heritageList.size - 1)
    }

    fun getItems(): List<HeritageAsset> {
        return heritageList
    }
}