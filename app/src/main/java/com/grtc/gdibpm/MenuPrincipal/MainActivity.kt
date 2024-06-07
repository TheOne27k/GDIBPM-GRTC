package com.grtc.gdibpm.MenuPrincipal


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.grtc.gdibpm.R

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val   recycleBienes = findViewById<RecyclerView>(R.id.recycleBienes)

        val listbienes = listOf<Main>(
            Main("Desplazamiento", R.drawable.desplazamientoo),
            Main("Ingresos", R.drawable.ingresoss),
            Main("Busqueda", R.drawable.lupaa) ,
            Main("Gestión de areas y Empleados", R.drawable.recursoss),
            Main("Dashboard", R.drawable.dashboardd)
        )



    }
}