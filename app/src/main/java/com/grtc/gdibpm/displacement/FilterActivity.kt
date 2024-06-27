package com.grtc.gdibpm.displacement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.R

class FilterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        val recyclerState = findViewById<RecyclerView>(R.id.recyclerStates)
        recyclerState.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerState.adapter = StatusAdapter(DisplacementStatus.values().toList())
    }
}
