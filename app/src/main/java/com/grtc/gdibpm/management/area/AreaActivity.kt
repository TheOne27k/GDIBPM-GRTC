package com.grtc.gdibpm.management.area

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.grtc.gdibpm.R
import com.grtc.gdibpm.management.ManagementActivity

class AreaActivity: AppCompatActivity(){
    private lateinit var areaViewModel: AreaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_area)
        areaViewModel = ViewModelProvider(this)[AreaViewModel::class.java]
        val edtAreaName = findViewById<TextInputEditText>(R.id.edtAreaName)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnCancel = findViewById<Button>(R.id.btnCancel)
        btnRegister.setOnClickListener {
            val areaName = edtAreaName.text.toString()
            val area = Area(id="", areaName)
            areaViewModel.registerArea(area)
            Intent(this, ManagementActivity::class.java).apply {
                startActivity(this)
            }
        }
        btnCancel.setOnClickListener {
            finish()
        }

    }
}