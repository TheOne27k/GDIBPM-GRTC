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
            Main("Desplazamiento", R.drawable.desplazamiento),
            Main("Ingresos", R.drawable.ingresos),
            Main("Busqueda", R.drawable.lupa) ,
            Main("Gestión de areas y Empleados", R.drawable.recursos),
            Main("Dashboard", R.drawable.dashboard)
        )



    }
}