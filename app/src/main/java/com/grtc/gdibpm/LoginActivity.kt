package com.grtc.gdibpm

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.grtc.gdibpm.main.MainFragment
import com.grtc.gdibpm.menu.MenuActivity

class LoginActivity: AppCompatActivity() {

    companion object {
        private const val VALID_EMAIL = "leomessi@gmail.com"
        private const val VALID_PASSWORD = "12345678"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email: EditText = findViewById(R.id.email)
        val password: TextInputEditText = findViewById(R.id.password)
        val passwordLayout: TextInputLayout = findViewById(R.id.password_layout)
        val loginButton: Button = findViewById(R.id.login_button)

        loginButton.setOnClickListener {
            val emailValue = email.text.toString().trim()
            val passwordValue = password.text.toString().trim()

            when {
                emailValue.isEmpty() -> {
                    Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
                }
                passwordValue.isEmpty() -> {
                    Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show()
                }
                emailValue == VALID_EMAIL && passwordValue == VALID_PASSWORD -> {
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }

        passwordLayout.setEndIconOnClickListener {
            if (password.inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }
            password.setSelection(password.text?.length ?: 0)
        }

        val forgotPasswordTextView: TextView = findViewById(R.id.forgot_password)
        forgotPasswordTextView.setOnClickListener {

            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}