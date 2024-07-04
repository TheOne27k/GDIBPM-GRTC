package com.grtc.gdibpm.management.user

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore
import com.grtc.gdibpm.R
import com.grtc.gdibpm.management.ManagementActivity
import com.grtc.gdibpm.management.area.AreaViewModel

class UserActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var areaViewModel: AreaViewModel
    private lateinit var firestore: FirebaseFirestore
    private lateinit var areaIdMap: Map<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_employee)

        val items = UserRole.values().map { it.rolName }
        val adapter = ArrayAdapter(this, R.layout.dropdown_menu_popup_item, items)
        val edtRole = findViewById<MaterialAutoCompleteTextView>(R.id.edtRole)
        edtRole.setAdapter(adapter)
        edtRole.dropDownHeight = resources.getDimensionPixelSize(R.dimen.dropdown_max_height)

        val edtArea = findViewById<MaterialAutoCompleteTextView>(R.id.edtArea)
        val areaAdapter = ArrayAdapter<String>(this, R.layout.dropdown_menu_popup_item)
        edtArea.setAdapter(areaAdapter)
        edtArea.dropDownHeight = resources.getDimensionPixelSize(R.dimen.dropdown_max_height)

        areaViewModel = ViewModelProvider(this).get(AreaViewModel::class.java)
        areaViewModel.areaListMutable.observe(this, Observer { areas ->
            areaAdapter.clear()
            areaAdapter.addAll(areas.map { it.name })
            areaIdMap = areas.associate { it.name to it.id }
            areaAdapter.notifyDataSetChanged()
        })
        areaViewModel.getArea()

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val edtName = findViewById<TextInputEditText>(R.id.edtName)
        val edtLastName = findViewById<TextInputEditText>(R.id.edtLastName)
        val edtDni = findViewById<TextInputEditText>(R.id.edtDni)
        val edtPhone = findViewById<TextInputEditText>(R.id.edtPhone)
        val edtEmail = findViewById<TextInputEditText>(R.id.edtEmail)
        val edtPassword = findViewById<TextInputEditText>(R.id.password)
        val edtConfirmPassword = findViewById<TextInputEditText>(R.id.confirmPassword)
        val btnRegister = findViewById<MaterialButton>(R.id.btnRegister)
        val btnCancel = findViewById<MaterialButton>(R.id.btnCancel)

        firestore = FirebaseFirestore.getInstance()

        btnRegister.setOnClickListener {
            val name = edtName.text.toString()
            val lastName = edtLastName.text.toString()
            val dni = edtDni.text.toString()
            val phone = edtPhone.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            val confirmPassword = edtConfirmPassword.text.toString()
            val role = UserRole.values().find { it.rolName == edtRole.text.toString() } ?: UserRole.USER
            val areaName = edtArea.text.toString()
            val areaId = areaIdMap[areaName]
            val areaRef = firestore.collection("areas").document(areaId!!)

            val user = User(name, lastName, phone, dni, email, areaRef,areaName, role)
            userViewModel.verifyRegister(user, password, confirmPassword)
            Intent(this, ManagementActivity::class.java).apply {
                startActivity(this)
            }
        }
        btnCancel.setOnClickListener {
            finish()
        }
    }
}