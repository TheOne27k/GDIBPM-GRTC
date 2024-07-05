package com.grtc.gdibpm.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.grtc.gdibpm.R

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val email: TextInputEditText = findViewById(R.id.etEmail)
        val btnSend: MaterialButton = findViewById(R.id.btnSend)
        loginViewModel = LoginViewModel()

        btnSend.setOnClickListener {
            val emailValue = email.text.toString().trim()
            if (emailValue.isEmpty()) {
                email.error = "Por favor, complete este campo"
            } else {
                loginViewModel.recoveryPassword(emailValue)
            }
        }
    }
}
