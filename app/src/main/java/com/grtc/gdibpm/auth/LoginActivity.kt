package com.grtc.gdibpm.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.grtc.gdibpm.R
import com.grtc.gdibpm.menu.MenuActivity

class LoginActivity: AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        val email: EditText = findViewById(R.id.email)
        val password: TextInputEditText = findViewById(R.id.password)
        val loginButton: Button = findViewById(R.id.login_button)
        val forgotPasswordTextView: TextView = findViewById(R.id.forgot_password)

        loginButton.setOnClickListener {
            val emailValue = email.text.toString().trim()
            val passwordValue = password.text.toString().trim()

            if (emailValue.isEmpty() || passwordValue.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.verifyLogin(emailValue, passwordValue)
            }
        }

        forgotPasswordTextView.setOnClickListener {

            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
        observerLiveData()
    }
    private fun observerLiveData(){
     viewModel.userLoginStatus.observe(this){
            if(it){
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Verificar sus datos", Toast.LENGTH_SHORT).show()
            }
     }
    }
}