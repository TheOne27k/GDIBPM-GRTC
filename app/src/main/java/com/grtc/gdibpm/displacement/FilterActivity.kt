package com.grtc.gdibpm.displacement

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.grtc.gdibpm.R

class FilterActivity : AppCompatActivity() {
    private val displacementViewModel: DisplacementViewModel by viewModels()
    private lateinit var displacementAdapter: DisplacementAdapter
    private lateinit var progressIndicator: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerDisplacements)
        recyclerView.layoutManager = LinearLayoutManager(this)
        displacementAdapter = DisplacementAdapter(emptyList())
        recyclerView.adapter = displacementAdapter

        // Obtener referencia al CircularProgressIndicator
        progressIndicator = findViewById(R.id.progressIndicator)

        displacementViewModel.filteredDisplacementListMutable.observe(this) { displacements ->
            displacementAdapter.updateDisplacements(displacements ?: emptyList())
            hideProgress()
        }

        findViewById<MaterialCardView>(R.id.cardViewAll).setOnClickListener {
            showProgress()
            displacementViewModel.showAllDisplacements()
        }
        findViewById<MaterialCardView>(R.id.cardViewCancelled).setOnClickListener {
            showProgress()
            displacementViewModel.filterByState(DisplacementStatus.CANCEL)
        }
        findViewById<MaterialCardView>(R.id.cardViewInProcess).setOnClickListener {
            showProgress()
            displacementViewModel.filterByState(DisplacementStatus.IN_PROCESS)
        }
        findViewById<MaterialCardView>(R.id.cardViewSuccess).setOnClickListener {
            showProgress()
            displacementViewModel.filterByState(DisplacementStatus.SUCCESS)
        }

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                showProgress()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                showProgress()
                displacementViewModel.filterByMotive(newText ?: "")
                return true
            }
        })

        // Obtener desplazamientos al iniciar la actividad
        showProgress()
        displacementViewModel.getDisplacements()
    }

    private fun showProgress() {
        progressIndicator.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progressIndicator.visibility = View.GONE
    }
}