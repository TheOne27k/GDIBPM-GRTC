package com.grtc.gdibpm.displacement

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.grtc.gdibpm.R

class FilterActivity : AppCompatActivity() {
    private val displacementViewModel: DisplacementViewModel by viewModels()
    private lateinit var displacementAdapter: DisplacementAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerDisplacements)
        recyclerView.layoutManager = LinearLayoutManager(this)
        displacementAdapter = DisplacementAdapter(emptyList())
        recyclerView.adapter = displacementAdapter

        displacementViewModel.filteredDisplacementListMutable.observe(this) { displacements ->
            displacementAdapter.updateDisplacements(displacements ?: emptyList())
        }

        findViewById<MaterialCardView>(R.id.cardViewAll).setOnClickListener {
            displacementViewModel.showAllDisplacements()
        }
        findViewById<MaterialCardView>(R.id.cardViewCancelled).setOnClickListener {
            displacementViewModel.filterByState(DisplacementStatus.CANCEL)
        }
        findViewById<MaterialCardView>(R.id.cardViewInProcess).setOnClickListener {
            displacementViewModel.filterByState(DisplacementStatus.IN_PROCESS)
        }
        findViewById<MaterialCardView>(R.id.cardViewSuccess).setOnClickListener {
            displacementViewModel.filterByState(DisplacementStatus.SUCCESS)
        }

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                displacementViewModel.filterByMotive(newText ?: "")
                return true
            }
        })

        displacementViewModel.getDisplacements()
    }
}
