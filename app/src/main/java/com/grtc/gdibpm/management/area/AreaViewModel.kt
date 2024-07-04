package com.grtc.gdibpm.management.area

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class AreaViewModel: ViewModel(){
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val areaListMutable = MutableLiveData<MutableList<Area>>()

    fun getArea(){
        firestore.collection("areas")
            .get()
            .addOnSuccessListener { result ->
                val listArea = mutableListOf<Area>()
                for(document in result) {
                    val id = document.id
                    val data = document.data

                    val name = data["name"] as String

                    val area = Area(id,name)
                    listArea.add(area)
                }
                areaListMutable.postValue(listArea)
            }
            .addOnFailureListener {
                areaListMutable.postValue(mutableListOf())
            }
    }

    fun registerArea(area: Area){
        val areaMap = hashMapOf(
            "name" to area.name
        )
        firestore.collection("areas")
            .add(areaMap)
            .addOnSuccessListener {
                getArea()
            }
            .addOnFailureListener {
                areaListMutable.postValue(mutableListOf())
            }
    }
}