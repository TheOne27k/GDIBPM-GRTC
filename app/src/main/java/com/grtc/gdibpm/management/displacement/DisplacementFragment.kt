package com.grtc.gdibpm.management.displacement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.R
import com.grtc.gdibpm.displacement.DisplacementViewModel

class DisplacementFragment : Fragment() {
    private lateinit var displacementViewModel: DisplacementViewModel
    private lateinit var adapter: DisplacementsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_displacement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displacementViewModel = ViewModelProvider(this)[DisplacementViewModel::class.java]

        val recyclerDisplacement = view.findViewById<RecyclerView>(R.id.recyclerDisplacements)
        recyclerDisplacement.layoutManager = LinearLayoutManager(context)
        adapter = DisplacementsAdapter()
        recyclerDisplacement.adapter = adapter

        displacementViewModel.displacementListMutable.observe(viewLifecycleOwner, Observer { displacements ->
            if (displacements != null) {
                adapter.setDisplacements(displacements)
            }
        })

        displacementViewModel.getDisplacements()
    }

    companion object {
        fun newInstance(): DisplacementFragment = DisplacementFragment()
    }
}
