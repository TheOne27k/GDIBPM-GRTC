package com.grtc.gdibpm.displacement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentReference
import com.grtc.gdibpm.R

class HeritagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val heritageCode: TextView = itemView.findViewById(R.id.heritageCode)
    private val heritageName: TextView = itemView.findViewById(R.id.heritageName)
    private val heritageState: TextView = itemView.findViewById(R.id.heritageState)

    fun bind(heritage: DocumentReference) {
        heritage.get().addOnSuccessListener { documentSnapshot ->
            heritageCode.text = documentSnapshot.getString("heritageCode") ?: "Unknown Code"
            heritageName.text = documentSnapshot.getString("name") ?: "Unknown Name"
            heritageState.text = documentSnapshot.getString("state") ?: "Unknown State"
        }.addOnFailureListener {
            heritageCode.text = "Error"
            heritageName.text = "Error"
            heritageState.text = "Error"
        }
    }

    companion object {
        fun create(parent: ViewGroup): HeritagesViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_heritage, parent, false)
            return HeritagesViewHolder(view)
        }
    }
}
