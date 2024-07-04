package com.grtc.gdibpm.management

import VPAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.grtc.gdibpm.R
import com.grtc.gdibpm.management.area.AreaFragment
import com.grtc.gdibpm.management.user.UserFragment

class ManagementActivity: AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_managment)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewpager)

        // Configurar ViewPager con TabLayout
        tabLayout.setupWithViewPager(viewPager)

        // Configurar el adaptador para ViewPager
        val vpAdapter = VPAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        vpAdapter.addFragment(UserFragment(), "Empleados")
        vpAdapter.addFragment(AreaFragment(), "Área")
        vpAdapter.addFragment(DisplacementFragment(), "Desplazamiento")
        viewPager.adapter = vpAdapter


    }

}   
