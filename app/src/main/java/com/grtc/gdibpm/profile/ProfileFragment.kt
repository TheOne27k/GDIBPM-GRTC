package com.grtc.gdibpm.profile

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.grtc.gdibpm.R
import com.grtc.gdibpm.management.user.User
import com.grtc.gdibpm.management.user.UserRole

class ProfileFragment : Fragment() {
    private lateinit var firestore: FirebaseFirestore
    private lateinit var nameTextView: TextView
    private lateinit var areaTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var telephoneTextView: TextView
    private lateinit var dniTextView: TextView
    private lateinit var roleTextView: TextView
    private lateinit var progressIndicator: LinearProgressIndicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressIndicator = view.findViewById(R.id.progressIndicator)
        nameTextView = view.findViewById(R.id.NameUser)
        areaTextView = view.findViewById(R.id.areaName)
        emailTextView = view.findViewById(R.id.emailUser)
        telephoneTextView = view.findViewById(R.id.phoneUser)
        dniTextView = view.findViewById(R.id.dniUser)
        roleTextView = view.findViewById(R.id.roleUser)
        firestore = FirebaseFirestore.getInstance()
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            showProgressIndicator(true)
            fetchUserData(userId)
        }
    }

    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }

    private fun fetchUserData(uid: String) {
        firestore.collection("users").document(uid)
            .get()
            .addOnSuccessListener { document ->
                val data = document.data
                data?.let { userData ->
                    val name = userData["name"] as? String ?: ""
                    val lastname = userData["lastname"] as? String ?: ""
                    val email = userData["email"] as? String ?: ""
                    val roleString = userData["role"] as? String ?: UserRole.USER.name
                    val telephone = userData["telephone"] as? String ?: ""
                    val dni = userData["dni"] as? String ?: ""

                    val areaRef = userData["areaRef"] as? DocumentReference

                    areaRef?.get()
                        ?.addOnSuccessListener { areaDoc ->
                            val areaName = areaDoc.getString("name") ?: "Área no especificada"
                            val role = try {
                                UserRole.valueOf(roleString)
                            } catch (e: IllegalArgumentException) {
                                UserRole.USER
                            }
                            val user = User(uid, name, lastname, telephone, dni, email, areaRef ?: firestore.document("areas/default"), areaName, role)
                            updateUI(user)
                            showProgressIndicator(false)
                        }
                        ?.addOnFailureListener { exception ->
                            Log.e(TAG, "Error al obtener el nombre del área para el usuario $name", exception)
                            showProgressIndicator(false)
                        }
                        ?: run {
                            val role = try {
                                UserRole.valueOf(roleString)
                            } catch (e: IllegalArgumentException) {
                                UserRole.USER
                            }
                            val defaultAreaRef = firestore.document("areas/default")
                            val user = User(uid, name, lastname, telephone, dni, email, defaultAreaRef, "Área no especificada", role)
                            updateUI(user)
                            showProgressIndicator(false)
                            Log.w(TAG, "El usuario $name no tiene área especificada")
                        }
                }
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error al obtener los datos del usuario", exception)
                showProgressIndicator(false)
            }
    }

    private fun updateUI(user: User) {
        nameTextView.text = "${user.name} ${user.lastname}"
        areaTextView.text = user.areaNane
        emailTextView.text = user.email
        telephoneTextView.text = user.telephone_number
        dniTextView.text = user.dni
        roleTextView.text = user.role.toString()
    }
    private fun showProgressIndicator(show: Boolean) {
        if (show) {
            progressIndicator.visibility = View.VISIBLE
        } else {
            progressIndicator.visibility = View.GONE
        }
    }
}
