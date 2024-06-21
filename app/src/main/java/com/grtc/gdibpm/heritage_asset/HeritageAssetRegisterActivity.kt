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
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.grtc.gdibpm.R

class HeritageAssetRegisterActivity: AppCompatActivity() {

    private val PICK_IMAGE_REQUEST_CODE = 1
    private lateinit var imagePreview: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_heritage_asset_register)

        val edtState = findViewById<MaterialAutoCompleteTextView>(R.id.edtState)

        val items = HeritageState.values().map { it.displayName }
        val adapter = ArrayAdapter(this, R.layout.dropdown_menu_popup_item, items)
        edtState.setAdapter(adapter)
        edtState.dropDownHeight = resources.getDimensionPixelSize(R.dimen.dropdown_max_height)

        imagePreview = findViewById<ImageView>(R.id.imgEvidence)

        findViewById<MaterialButton>(R.id.btnAddEvidence).setOnClickListener {
            openImagePicker()
        }

        val btnCancel = findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener {
            finish()
        }
    }
    private fun registerHeritageAsset() {
//        val edtCode = findViewById<TextInputEditText>(R.id.edtCode)
//        val edtName = findViewById<TextInputEditText>(R.id.edtName)
//        val edtBrand = findViewById<TextInputEditText>(R.id.edtBrand)
//        val edtModel = findViewById<TextInputEditText>(R.id.edtModel)
//        val edtSerial = findViewById<TextInputEditText>(R.id.edtSerial)
//        val edtColor = findViewById<TextInputEditText>(R.id.edtColor)
//        val edtState = findViewById<MaterialAutoCompleteTextView>(R.id.edtState)
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
}