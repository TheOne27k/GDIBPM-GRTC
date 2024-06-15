package com.grtc.gdibpm

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.aprendizaje.practicaconbd.AreaFragment
import com.aprendizaje.practicaconbd.EmpleadoFragment
import com.aprendizaje.practicaconbd.EstadoFragment
import com.aprendizaje.practicaconbd.FechaFragment
import com.aprendizaje.practicaconbd.PatrimonioFragment
import com.google.android.material.navigation.NavigationView

class FilterActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, PatrimonioFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_codigo_patrimonial)
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    finish()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.buscador_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterNavigationView(newText)
                return true
            }
        })
        return true
    }

    private fun filterNavigationView(query: String?) {
        try {
            val menu = navigationView.menu
            for (i in 0 until menu.size()) {
                val menuItem = menu.getItem(i)
                val visible = menuItem.title.toString().contains(query ?: "", ignoreCase = true)
                menuItem.isVisible = visible
                if (menuItem.hasSubMenu()) {
                    val subMenu = menuItem.subMenu
                    if (subMenu != null) {
                        for (j in 0 until subMenu.size()) {
                            val subMenuItem = subMenu.getItem(j)
                            subMenuItem.isVisible = subMenuItem.title.toString().contains(query ?: "", ignoreCase = true)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_codigo_patrimonial -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PatrimonioFragment()).commit()

            R.id.nav_area -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AreaFragment()).commit()

            R.id.nav_empleado -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EmpleadoFragment()).commit()

            R.id.nav_estado -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, EstadoFragment()).commit()

            R.id.nav_fecha_desplazamiento -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FechaFragment()).commit()

            R.id.nav_logout -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
