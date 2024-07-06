package com.grtc.gdibpm.management.user

import android.content.ContentValues.TAG
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class UserViewModel: ViewModel() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    val userMutable = MutableLiveData<MutableList<User>>()
    val validationError = MutableLiveData<String>()

    val registrationStatus = MutableLiveData<Boolean>()
    fun verifyRegister(user: User, password: String, confirmPassword: String){
        if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
            registrationStatus.value = false
        } else if (password.length < 5) {
            validationError.value = "La contraseña debe tener al menos 5 caracteres"
            registrationStatus.value = false
        } else if (password != confirmPassword) {
            validationError.value = "Las contraseñas no coinciden"
            registrationStatus.value = false
        }else if (user.dni.length != 8) {
            validationError.value = "El dni debe tener 8 caracteres"
            registrationStatus.value = false
        }else if (user.telephone_number.length != 9) {
            validationError.value = "El número de teléfono debe tener 9 caracteres"
            registrationStatus.value = false
        }else if (user.name.isEmpty() && user.lastname.isEmpty() && user.email.isEmpty() && user.dni.isEmpty() && user.telephone_number.isEmpty()){
            validationError.value = "Todos los campos son requeridos"
            registrationStatus.value = false
        }
        else {
            registerFirebase(user , password)
        }
    }
    private fun registerFirebase(user: User, password: String) {
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(user.email, password)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    val uid = it.result?.user?.uid
                    uid?.let {
                        registerFireStore(uid,user)
                    }
                }else{
                    registrationStatus.value = false
                }
                registrationStatus.value = it.isSuccessful
            }
    }
    private fun registerFireStore(uid: String, user: User) {
        firestore = FirebaseFirestore.getInstance()
        val user = hashMapOf(
            "name" to user.name,
            "lastname" to user.lastname,
            "email" to user.email,
            "role" to user.role.name,
            "telephone" to  user.telephone_number,
            "dni" to user.dni,
            "areaRef" to user.areaRef
        )
        firestore.collection("users").document(uid)
            .set(user)
            .addOnSuccessListener {
                registrationStatus.value = true
            }
            .addOnFailureListener {
                registrationStatus.value = false
            }
    }
    fun listenForUserUpdates() {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("users").addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                userMutable.value = mutableListOf()
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {
                val listUsers = mutableListOf<User>()
                for (document in snapshot.documents) {
                    val id = document.id
                    val data = document.data

                    val name = data?.get("name") as String
                    val lastname = data["lastname"] as String
                    val email = data["email"] as String
                    val roleString = data["role"] as String
                    val telephone = data["telephone"] as String
                    val dni = data["dni"] as String

                    val areaRef = data["areaRef"] as? DocumentReference

                    if (areaRef != null) {
                        areaRef.get()
                            .addOnSuccessListener { areaDoc ->
                                val areaName = areaDoc.getString("name")
                                if (areaName != null) {
                                    val role = try {
                                        UserRole.valueOf(roleString)
                                    } catch (e: IllegalArgumentException) {
                                        UserRole.USER
                                    }
                                    val user = User(id, name, lastname, telephone, dni, email, areaRef, areaName, role)
                                    listUsers.add(user)
                                    userMutable.postValue(listUsers)
                                } else {
                                    validationError.value = "El nombre del área es nulo para el usuario"
                                }
                            }
                            .addOnFailureListener { exception ->
                                validationError.value = "Error al obtener el nombre del área para el usuario"
                            }
                    } else {
                        Log.w(TAG, "areaRef es nulo para el usuario $name")
                    }
                }
                userMutable.postValue(listUsers)
            } else {
                Log.d(TAG, "Current data: null")
                userMutable.value = mutableListOf()
            }
        }
    }
}