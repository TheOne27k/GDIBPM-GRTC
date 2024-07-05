package com.grtc.gdibpm.displacement

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class DisplacementViewModel : ViewModel() {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val displacementListMutable = MutableLiveData<List<Displacement>>()
    val registrationStatus = MutableLiveData<Boolean>()

    fun registerDisplacement(displacement: Displacement) {
        val displacementMap = hashMapOf(
            "date" to displacement.date,
            "heritage" to displacement.listHeritage,
            "motive" to displacement.motive,
            "receiver" to displacement.receiver,
            "sender" to displacement.sender,
            "state" to displacement.state.name
        )

        firestore.collection("displacements")
            .add(displacementMap)
            .addOnSuccessListener {
                registrationStatus.value = true
            }
            .addOnFailureListener {
                registrationStatus.value = false
            }
    }

    fun getDisplacements() {
        firestore.collection("displacements")
            .get()
            .addOnSuccessListener { result ->
                val displacements = mutableListOf<Displacement>()
                for (document in result) {
                    val data = document.data
                    val date = data["date"] as? String ?: ""
                    val heritage = data["heritage"] as? List<DocumentReference> ?: emptyList()
                    val motive = data["motive"] as? String ?: ""
                    val receiverRef = data["receiver"] as? DocumentReference
                    val senderRef = data["sender"] as? DocumentReference
                    val stateStr = data["state"] as? String ?: DisplacementStatus.IN_PROCESS.name

                    if (receiverRef != null && senderRef != null) {
                        senderRef.get().addOnSuccessListener { senderSnapshot ->
                            val senderName = senderSnapshot.getString("name") ?: "Unknown Sender"
                            receiverRef.get().addOnSuccessListener { receiverSnapshot ->
                                val receiverName = receiverSnapshot.getString("name") ?: "Unknown Receiver"
                                val state = DisplacementStatus.valueOf(stateStr)
                                val displacement = Displacement(senderRef, receiverRef, motive, heritage, date, state)
                                displacement.senderName = senderName
                                displacement.receiverName = receiverName
                                displacements.add(displacement)
                                displacementListMutable.value = displacements
                            }
                        }
                    } else {
                        Log.e("Firestore", "Receiver or Sender is null")
                    }
                }
                displacementListMutable.value = displacements
            }
            .addOnFailureListener {
                displacementListMutable.value = emptyList()
            }
    }
}
