package com.grtc.gdibpm.auth

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel:ViewModel() {
    private lateinit var auth: FirebaseAuth
    val userLoginStatus = MutableLiveData<Boolean>()
    fun verifyLogin(email: String, password: String) {
        if(email.isEmpty() && password.isEmpty()) {
            userLoginStatus.value = false
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userLoginStatus.value = false
        }
        else {
            loginFirebase(email, password)
        }
    }
    private fun loginFirebase(email: String, password: String) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                userLoginStatus.value = it.isSuccessful
            }
    }
    fun recoveryPassword(email: String) {
        auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener {
                userLoginStatus.value = it.isSuccessful
            }
    }
}