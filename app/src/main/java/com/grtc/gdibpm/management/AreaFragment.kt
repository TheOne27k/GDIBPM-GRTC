package com.grtc.gdibpm.management

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.grtc.gdibpm.R


class areaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list_area, container, false)

        val btnAddArea = view.findViewById<Button>(R.id.btnAddArea)

        btnAddArea.setOnClickListener {

            val intent = Intent(activity, AreaActivity::class.java)
            startActivity(intent)
        }
        return view
    }

}