package com.grtc.gdibpm

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.heritage_asset.Heritagedapter
import com.grtc.gdibpm.heritage_asset.HeritageAsset

class DisplacementRegisterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_displacement_register)
        val recyclerHeritage = findViewById<RecyclerView>(R.id.recyclerHeritageAssets)

        val listHeritage = listOf<HeritageAsset>()

        val adapter = Heritagedapter(listHeritage)
        recyclerHeritage.adapter = adapter
        recyclerHeritage.layoutManager = LinearLayoutManager(this)

        val btnCancel = findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            finish()
        }
    }
}