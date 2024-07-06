package com.grtc.gdibpm.heritage_asset

import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.grtc.gdibpm.R
import com.grtc.gdibpm.main.MainFragment

class HeritageAssetRegisterActivity : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST_CODE = 1
    private lateinit var imagePreview: ImageView
    private var imageUri: Uri? = null
    private lateinit var heritageViewModel: HeritageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_heritage_asset_register)

        heritageViewModel = ViewModelProvider(this).get(HeritageViewModel::class.java)

        val items = HeritageState.values().map { it.displayName }
        val adapter = ArrayAdapter(this, R.layout.dropdown_menu_popup_item, items)

        imagePreview = findViewById(R.id.imgEvidence)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val edtState = findViewById<MaterialAutoCompleteTextView>(R.id.edtState)
        edtState.setAdapter(adapter)
        edtState.dropDownHeight = resources.getDimensionPixelSize(R.dimen.dropdown_max_height)

        findViewById<MaterialButton>(R.id.btnAddEvidence).setOnClickListener {
            openImagePicker()
        }
        btnRegister.setOnClickListener {
            registerHeritageAsset()
        }

        val btnCancel = findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            finish()
        }

        heritageViewModel.heritageListMutable.observe(this, Observer { heritageList ->
            showToast("La lista de bienes fue actualizada: $heritageList")
        })

        heritageViewModel.validationError.observe(this, Observer { error ->
            error?.let {
                showToast(it)
            }
        })
        heritageViewModel.registrationStatus.observe(this, Observer { isSuccess ->
            if (isSuccess == true) {
                redirectToMainPage()
            }
        })
    }

    private fun registerHeritageAsset() {
        val edtCode = findViewById<TextInputEditText>(R.id.edtCode)
        val edtName = findViewById<TextInputEditText>(R.id.edtName)
        val edtBrand = findViewById<TextInputEditText>(R.id.edtBrand)
        val edtModel = findViewById<TextInputEditText>(R.id.edtModel)
        val edtSerial = findViewById<TextInputEditText>(R.id.edtSerial)
        val edtColor = findViewById<TextInputEditText>(R.id.edtColor)
        val edtState = findViewById<MaterialAutoCompleteTextView>(R.id.edtState)

        val code = edtCode.text.toString().trim()
        val name = edtName.text.toString().trim()
        val brand = edtBrand.text.toString().trim()
        val model = edtModel.text.toString().trim()
        val serial = edtSerial.text.toString().trim()
        val color = edtColor.text.toString().trim()
        val stateDisplayName = edtState.text.toString().trim()

        val stateEnum = HeritageState.values().find { it.displayName == stateDisplayName }

        if (stateEnum == null) {
            showToast("Estado no válido: $stateDisplayName")
            return
        }

        imageUri?.let { uri ->
            heritageViewModel.uploadImageToFirebase(uri) { success, downloadUrl ->
                if (success) {
                    val heritageAsset = HeritageAsset(
                        code, name, brand, model, serial, color,
                        stateEnum, downloadUrl ?: ""
                    )
                    heritageViewModel.verifyRegister(heritageAsset)
                } else {
                    showToast("Error al subir la imagen")
                }
            }
        } ?: run {
            showToast("No se ha seleccionado una imagen")
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.data?.also { uri ->
                imageUri = uri
                displayImage(uri)
            }
        }
    }

    private fun displayImage(uri: Uri) {
        try {
            val bitmap = if (Build.VERSION.SDK_INT < 28) {
                @Suppress("DEPRECATION")
                MediaStore.Images.Media.getBitmap(contentResolver, uri)
            } else {
                val source = ImageDecoder.createSource(contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            }
            imagePreview.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun redirectToMainPage() {
        val intent = Intent(this, MainFragment::class.java)
        startActivity(intent)
        finish()
    }
}
