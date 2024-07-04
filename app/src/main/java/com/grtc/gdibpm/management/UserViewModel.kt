package com.grtc.gdibpm.management

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserViewModel: ViewModel() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    val registrationStatus = MutableLiveData<Boolean>()
    fun verifyRegister(name: String, lastName: String, email: String, password: String) {
        if(email.isEmpty() && password.isEmpty()) {
            registrationStatus.value = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            registrationStatus.value = false
        } else if (password.length < 5) {
            registrationStatus.value = false
        } else {
            registerFirebase(name, lastName, email, password)
        }
    }
    private fun registerFirebase(name: String, lastName: String, email: String, password: String) {
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    val uid = it.result?.user?.uid
                    uid?.let {
                        registerFireStore(uid, name, lastName, email)
                    }
                }else{
                    registrationStatus.value = false
                }
                registrationStatus.value = it.isSuccessful
            }
    }
    private fun registerFireStore(uid: String, name: String, lastName: String, email: String) {
        firestore = FirebaseFirestore.getInstance()
        val user = hashMapOf(
            "nombre" to name,
            "apellido" to lastName,
            "correo" to email
        )
        firestore.collection("usuarios").document(uid)
            .set(user)
            .addOnSuccessListener {
                registrationStatus.value = true
            }
            .addOnFailureListener {
                registrationStatus.value = false
            }
    }
}