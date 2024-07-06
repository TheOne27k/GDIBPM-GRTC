package com.grtc.gdibpm.heritage_asset

import android.net.Uri
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class HeritageViewModel : ViewModel() {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val storageReference: StorageReference = FirebaseStorage.getInstance().reference
    val heritageListMutable = MutableLiveData<HeritageAsset>()
    val registrationStatus = MutableLiveData<Boolean>()
    val validationError = MutableLiveData<String>()

    fun verifyRegister(heritage: HeritageAsset) {
        when {
            heritage.HeritageCode.isEmpty() -> {
                validationError.value = "El código del patrimonio no puede estar vacío"
                registrationStatus.value = false
            }
            heritage.HeritageCode.length != 13 -> {
                validationError.value = "El código del patrimonio debe tener 13 caracteres"
                registrationStatus.value = false
            }
            else -> {
                registerHeritageAsset(heritage)
            }
        }
    }

    fun getHeritageByCode(code: String) {
        firestore.collection("heritageAsset")
            .whereEqualTo("heritageCode", code)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    return@addSnapshotListener
                }

                val heritageList = mutableListOf<HeritageAsset>()
                for (document in snapshots!!) {
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
                        heritageEvidence,
                        document.reference
                    )
                    heritageList.add(heritage)
                }
                heritageListMutable.value = heritageList.firstOrNull()
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
                registrationStatus.value = true
            }
            .addOnFailureListener {
                registrationStatus.value = false
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
