package com.grtc.gdibpm.management.user

import android.content.Intent
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

class UserFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAddEmpleado = view.findViewById<Button>(R.id.btnAddEmpleado)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val recyclerEmpleados = view.findViewById<RecyclerView>(R.id.recyclerEmpleados)
        val adapter = UserAdapter()
        recyclerEmpleados.adapter = adapter
        recyclerEmpleados.layoutManager = LinearLayoutManager(activity)
        userViewModel.userMutable.observe(viewLifecycleOwner, Observer { user ->
            adapter.setUsers(user)
        })
        userViewModel.getUsers()

        btnAddEmpleado.setOnClickListener {

            val intent = Intent(activity, UserActivity::class.java)
            startActivity(intent)
        }
    }
}