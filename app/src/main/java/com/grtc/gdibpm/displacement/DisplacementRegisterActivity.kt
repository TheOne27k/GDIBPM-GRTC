package com.grtc.gdibpm.displacement

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.firebase.firestore.FirebaseFirestore
import com.grtc.gdibpm.R
import com.grtc.gdibpm.databinding.ActivityDisplacementRegisterBinding
import com.grtc.gdibpm.heritage_asset.HeritageAdapter
import com.grtc.gdibpm.heritage_asset.HeritageViewModel
import com.grtc.gdibpm.management.user.UserViewModel
import com.grtc.gdibpm.menu.MenuActivity

class DisplacementRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplacementRegisterBinding
    private lateinit var displacementViewModel: DisplacementViewModel
    private val heritageViewModel: HeritageViewModel by viewModels()
    private lateinit var heritageAdapter: HeritageAdapter
    private lateinit var userViewModel: UserViewModel
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var userIdMap: Map<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDisplacementRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displacementViewModel = ViewModelProvider(this)[DisplacementViewModel::class.java]

        setupRecyclerView()
        setupObservers()

        binding.btnAddHeritageAsset.setOnClickListener {
            val heritageCode = binding.txtHeritageCode.text.toString()
            if (heritageCode.isNotEmpty()) {
                heritageViewModel.getHeritageByCode(heritageCode)
            }
        }

        val edtSender = findViewById<MaterialAutoCompleteTextView>(R.id.edtSender)
        val edtReceiver = findViewById<MaterialAutoCompleteTextView>(R.id.edtReceiver)
        val userAdapter = ArrayAdapter<String>(this, R.layout.dropdown_menu_popup_item)
        edtSender.setAdapter(userAdapter)
        edtSender.dropDownHeight = resources.getDimensionPixelSize(R.dimen.dropdown_max_height)
        edtReceiver.setAdapter(userAdapter)
        edtReceiver.dropDownHeight = resources.getDimensionPixelSize(R.dimen.dropdown_max_height)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.listenForUserUpdates()
        userViewModel.userMutable.observe(this, Observer { users ->
            userAdapter.clear()
            userAdapter.addAll(users.map { "${it.name} ${it.lastname}" })
            userIdMap = users.associate { "${it.name} ${it.lastname}" to it.id }
            userAdapter.notifyDataSetChanged()
        })

        val btnCancel = findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            finish()
        }

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener {
            registerDisplacement()
            Intent(this, MenuActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    private fun setupRecyclerView() {
        heritageAdapter = HeritageAdapter(mutableListOf())
        binding.recyclerHeritageAssets.apply {
            layoutManager = LinearLayoutManager(this@DisplacementRegisterActivity)
            adapter = heritageAdapter
        }
    }

    private fun setupObservers() {
        heritageViewModel.heritageListMutable.observe(this) { heritageAsset ->
            heritageAsset?.let {
                heritageAdapter.addItem(it)
            }
        }
    }

    private fun registerDisplacement() {
        val senderName = binding.edtSender.text.toString()
        val receiverName = binding.edtReceiver.text.toString()
        val motive = binding.txtMotive.text.toString()

        val senderRef = userIdMap[senderName]?.let { id ->
            firestore.collection("users").document(id)
        }
        val receiverRef = userIdMap[receiverName]?.let { id ->
            firestore.collection("users").document(id)
        }

        if (senderRef != null && receiverRef != null) {
            val heritageRefs = heritageAdapter.getItems().map { heritageAsset ->
                heritageAsset.documentReference
            }

            val displacement = Displacement(
                sender = senderRef,
                receiver = receiverRef,
                motive = motive,
                listHeritage = heritageRefs
            )

            displacementViewModel.registerDisplacement(displacement)
        } else {
            // Manejar error si los campos son nulos
        }
    }
}
