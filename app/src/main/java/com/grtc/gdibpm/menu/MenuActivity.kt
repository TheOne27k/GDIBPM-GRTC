package com.grtc.gdibpm.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
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
    }
fun openFragment(fragment: androidx.fragment.app.Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.commit()
    }
}