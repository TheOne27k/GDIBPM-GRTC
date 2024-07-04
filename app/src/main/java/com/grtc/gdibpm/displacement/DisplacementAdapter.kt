package com.grtc.gdibpm.displacement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DisplacementAdapter() :                                                                                                                                                                                                   RecyclerView.Adapter<DisplacementViewHolder>(){
        private var heritageList = emptyList<Displacement>()
        fun setHeritage(heritage: List<Displacement>){
            heritageList = heritage
            notifyDataSetChanged()
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplacementViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DisplacementViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return heritageList.size
        }

    override fun onBindViewHolder(holder: DisplacementViewHolder, position: Int) {
        val displacement = heritageList[position]
        holder.data(displacement)
    }

}