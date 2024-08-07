package com.grtc.gdibpm.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.grtc.gdibpm.dashboard.DashboardActivity
import com.grtc.gdibpm.displacement.FilterActivity
import com.grtc.gdibpm.R
import com.grtc.gdibpm.management.ManagementActivity

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val cardDashboard = view.findViewById<MaterialCardView>(R.id.cardDashboard)
        val cardDisplacement = view.findViewById<MaterialCardView>(R.id.cardDisplacement)
        val cardManagment = view.findViewById<MaterialCardView>(R.id.cardManagment)

//        cardDashboard.setOnClickListener {
//            val intent = Intent(context, DashboardActivity::class.java)
//            startActivity(intent)
//        }
        cardDisplacement.setOnClickListener {
            val intent = Intent(context, FilterActivity::class.java)
            startActivity(intent)
        }
        cardManagment.setOnClickListener {
            val intent = Intent(context, ManagementActivity::class.java)
            startActivity(intent)
        }
    }
    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }
}