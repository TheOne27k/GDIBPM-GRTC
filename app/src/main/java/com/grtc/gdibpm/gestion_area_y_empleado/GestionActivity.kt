package com.grtc.gdibpm.gestion_area_y_empleado

import VPAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.grtc.gdibpm.R

class GestionActivity: AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewpager)

        // Configurar ViewPager con TabLayout
        tabLayout.setupWithViewPager(viewPager)

        // Configurar el adaptador para ViewPager
        val vpAdapter = VPAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        vpAdapter.addFragment(empleadoFragment(), "Empleados")
        vpAdapter.addFragment(areaFragment(), "Área")
        viewPager.adapter = vpAdapter


    }

}