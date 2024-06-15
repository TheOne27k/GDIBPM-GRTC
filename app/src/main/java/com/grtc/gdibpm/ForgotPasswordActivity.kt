package com.grtc.gdibpm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var secretCodeForm: LinearLayout
    private lateinit var changePasswordForm: LinearLayout
    private lateinit var codSecreto: TextInputEditText
    private lateinit var btnSend: Button
    private lateinit var etNewPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        secretCodeForm = findViewById(R.id.secretCodeForm)
        changePasswordForm = findViewById(R.id.changePasswordForm)
        codSecreto = findViewById(R.id.codSecreto)
        btnSend = findViewById(R.id.btnSend)
        etNewPassword = findViewById(R.id.etNewPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnSave = findViewById(R.id.btnSave)

        btnSend.setOnClickListener {
            onEnviarClick()
        }
        btnSave.setOnClickListener {
            onGuardarClick()
        }
    }

    private fun onEnviarClick() {
        val secretCode = codSecreto.text.toString()
        if (validateSecretCode(secretCode)) {
            secretCodeForm.visibility = View.GONE
            changePasswordForm.visibility = View.VISIBLE
        } else {
            codSecreto.error = "Código secreto incorrecto"
        }
    }

    private fun validateSecretCode(secretCode: String): Boolean {
        return secretCode == "123456"
    }

    private fun onGuardarClick() {
        val newPassword = etNewPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()

        if (validatePasswords(newPassword, confirmPassword)) {
            val intent = Intent(this@ForgotPasswordActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            etConfirmPassword.error = "Las contraseñas no coinciden"
        }
    }

    private fun validatePasswords(newPassword: String, confirmPassword: String): Boolean {
        return newPassword.isNotEmpty() && newPassword == confirmPassword
    }
}
