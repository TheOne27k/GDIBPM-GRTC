package com.grtc.gdibpm.displacement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class DisplacementViewModel: ViewModel(){
    private lateinit var firestore: FirebaseFirestore
    val displacementListMutable = MutableLiveData<List<Displacement>>()
    var displacementList = arrayListOf<Displacement>()
    val registrationStatus = MutableLiveData<Boolean>()

    fun registerDisplacement(displacement: Displacement) {
        firestore = FirebaseFirestore.getInstance()
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
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("displacements")
            .get()
            .addOnSuccessListener { result ->
                val movements = mutableListOf<Displacement>()
                for (document in result) {
                    val data = document.data
                    val date = data["date"] as String
                    val heritage = data["heritage"] as List<DocumentReference>
                    val motive = data["motive"] as String
                    val receiver = data["receptor"] as DocumentReference
                    val sender = data["remitente"] as DocumentReference
                    val state = data["state"] as String

                    val displacement = Displacement(sender, receiver, motive, heritage, date, DisplacementStatus.valueOf(state))
                    displacementList.add(displacement)
                }
                displacementListMutable.value = displacementList
            }
            .addOnFailureListener {
                displacementListMutable.value = emptyList()
            }
    }
}