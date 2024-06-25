package com.grtc.gdibpm.gestion_area_y_empleado

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.grtc.gdibpm.R

class empleadoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_list_users, container, false)

        val btnAddEmpleado = view.findViewById<Button>(R.id.btnAddEmpleado)

        btnAddEmpleado.setOnClickListener {

            val intent = Intent(activity, empleadoClass::class.java)
            startActivity(intent)
        }
        return view
    }

}