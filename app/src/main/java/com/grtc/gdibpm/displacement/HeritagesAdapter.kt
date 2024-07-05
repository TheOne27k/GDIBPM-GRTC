package com.grtc.gdibpm.displacement

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentReference

class HeritagesAdapter(private val heritageList: List<DocumentReference>) : RecyclerView.Adapter<HeritagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeritagesViewHolder {
        return HeritagesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HeritagesViewHolder, position: Int) {
        holder.bind(heritageList[position])
    }

    override fun getItemCount(): Int = heritageList.size
}
