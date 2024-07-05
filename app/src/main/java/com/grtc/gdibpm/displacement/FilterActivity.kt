package com.grtc.gdibpm.displacement

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.R

class FilterActivity : AppCompatActivity() {
    private val displacementViewModel: DisplacementViewModel by viewModels()
    private lateinit var displacementAdapter: DisplacementAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerDisplacements)
        recyclerView.layoutManager = LinearLayoutManager(this)

        displacementViewModel.displacementListMutable.observe(this) { displacements ->
            displacementAdapter = DisplacementAdapter(displacements)
            recyclerView.adapter = displacementAdapter
        }

        displacementViewModel.getDisplacements()
    }
}
