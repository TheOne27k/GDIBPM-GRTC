package com.grtc.gdibpm.management.area

import AreaViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.R

class AreaFragment : Fragment() {
    private lateinit var areaViewModel: AreaViewModel
    private lateinit var adapter: AreaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_area, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        areaViewModel = ViewModelProvider(this).get(AreaViewModel::class.java)

        val recyclerArea = view.findViewById<RecyclerView>(R.id.recyclerArea)
        adapter = AreaAdapter { position ->
            val areaToDelete = adapter.getItem(position)
            areaViewModel.deleteArea(areaToDelete)
        }

        recyclerArea.adapter = adapter
        recyclerArea.layoutManager = LinearLayoutManager(activity)

        areaViewModel.areaListMutable.observe(viewLifecycleOwner, Observer { areas ->
            areas?.let { adapter.setArea(it) }
        })

        val btnAddArea = view.findViewById<Button>(R.id.btnAddArea)
        btnAddArea.setOnClickListener {
            // Lógica para agregar nueva área
        }
    }

    companion object {
        fun newInstance(): AreaFragment = AreaFragment()
    }
}
