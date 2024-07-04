package com.grtc.gdibpm.management

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.grtc.gdibpm.R

class EmpleadoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAddEmpleado = view.findViewById<Button>(R.id.btnAddEmpleado)

        btnAddEmpleado.setOnClickListener {

            val intent = Intent(activity, EmpleadoActivity::class.java)
            startActivity(intent)
        }
    }

}