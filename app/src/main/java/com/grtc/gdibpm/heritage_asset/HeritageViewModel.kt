package com.grtc.gdibpm.heritage_asset

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class HeritageViewModel : ViewModel() {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val storageReference: StorageReference = FirebaseStorage.getInstance().reference
    val heritageListMutable = MutableLiveData<HeritageAsset?>()

    fun getHeritageByCode(code: String) {
        firestore.collection("heritageAsset")
            .whereEqualTo("heritageCode", code)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val data = document.data

                    val heritageCode = data["heritageCode"] as String
                    val heritageName = data["name"] as String
                    val heritageBrand = data["brand"] as String
                    val heritageModel = data["model"] as String
                    val heritageSerial = data["serial"] as String
                    val heritageColor = data["color"] as String
                    val heritageStateString = data["state"] as String
                    val heritageEvidence = data["evidence"] as String

                    val heritageState = HeritageState.valueOf(heritageStateString)

                    val heritage = HeritageAsset(
                        heritageCode,
                        heritageName,
                        heritageBrand,
                        heritageModel,
                        heritageSerial,
                        heritageColor,
                        heritageState,
                        heritageEvidence
                    )
                    heritageListMutable.value = heritage
                }
            }
            .addOnFailureListener {
                heritageListMutable.value = null
            }
    }

    fun registerHeritageAsset(heritage: HeritageAsset) {
        val heritageMap = hashMapOf(
            "heritageCode" to heritage.HeritageCode,
            "name" to heritage.HeritageName,
            "brand" to heritage.HeritageBrand,
            "model" to heritage.HeritageModel,
            "serial" to heritage.HeritageSerial,
            "color" to heritage.HeritageColor,
            "state" to heritage.HeritageState.name,
            "evidence" to heritage.HeritageEvidence
        )
        firestore.collection("heritageAsset")
            .add(heritageMap)
            .addOnSuccessListener {
                heritageListMutable.value = heritage
            }
            .addOnFailureListener {
                heritageListMutable.value = null
            }
    }

    fun uploadImageToFirebase(imageUri: Uri, callback: (Boolean, String?) -> Unit) {
        val fileName = "images/${System.currentTimeMillis()}.jpg"
        val imageRef = storageReference.child(fileName)
        val uploadTask = imageRef.putFile(imageUri)

        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                callback(true, uri.toString())
            }.addOnFailureListener {
                callback(false, null)
            }
        }.addOnFailureListener {
            callback(false, null)
        }
    }
}
