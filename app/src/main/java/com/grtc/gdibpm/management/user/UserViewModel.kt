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

    val registrationStatus = MutableLiveData<Boolean>()
    fun verifyRegister(user: User, password: String, confirmPassword: String){
        if(user.email.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()){
            registrationStatus.value = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
            registrationStatus.value = false
        } else if (password.length < 5) {
            registrationStatus.value = false
        } else if (password != confirmPassword) {
            registrationStatus.value = false
        } else {
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
    fun getUsers() {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("users")
            .get()
            .addOnSuccessListener { result ->
                val listUsers = mutableListOf<User>()
                for (document in result) {
                    val id = document.id
                    val data = document.data

                    val name = data["name"] as String
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
                                    val user = User(name, lastname, telephone, dni, email, areaRef,areaName, role)
                                    listUsers.add(user)
                                    userMutable.postValue(listUsers)
                                } else {
                                    Log.w(TAG, "Nombre del área es nulo para el usuario $name")
                                }
                            }
                            .addOnFailureListener { exception ->
                                Log.e(TAG, "Error al obtener el nombre del área para el usuario $name", exception)
                            }
                    } else {
                        Log.w(TAG, "areaRef es nulo para el usuario $name")
                    }
                }
                if (listUsers.isNotEmpty()) {
                    userMutable.postValue(listUsers)
                } else {
                    Log.w(TAG, "No se encontraron usuarios en la base de datos.")
                }
            }
            .addOnFailureListener { exception ->
                userMutable.postValue(mutableListOf())
                Log.e(TAG, "Error al obtener la lista de usuarios", exception)
            }
    }
}