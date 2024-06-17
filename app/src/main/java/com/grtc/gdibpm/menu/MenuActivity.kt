package com.grtc.gdibpm.menu

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.grtc.gdibpm.DisplacementRegisterActivity
import com.grtc.gdibpm.HeritageAssetRegisterActivity
import com.grtc.gdibpm.R
import com.grtc.gdibpm.main.MainFragment
import com.grtc.gdibpm.profile.ProfileFragment

class MenuActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val nav_view = findViewById<BottomNavigationView>(R.id.botton_navigation)
        nav_view.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    val fragment = MainFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                R.id.profile -> {
                    val fragment = ProfileFragment.newInstance()
                    openFragment(fragment)
                    true
                }
                else -> false
            }
        }
        nav_view.selectedItemId = R.id.home
        val fab: View = findViewById(R.id.floatingCreation)
        fab.setOnClickListener {
            showBottomDialog()
        }
    }
    fun openFragment(fragment: androidx.fragment.app.Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.commit()
    }

    private fun showBottomDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_layout)

        val layoutHeritage: LinearLayout = dialog.findViewById(R.id.layoutHeritage)
        val layoutDisplacement: LinearLayout = dialog.findViewById(R.id.layoutDisplacement)
        val cancelButton: ImageView = dialog.findViewById(R.id.cancelButton)

        layoutHeritage.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(this, HeritageAssetRegisterActivity::class.java)
            startActivity(intent)
        }

        layoutDisplacement.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(this, DisplacementRegisterActivity::class.java)
            startActivity(intent)
        }

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes.windowAnimations = R.style.DialogAnimation
            setGravity(Gravity.BOTTOM)
        }
    }
}