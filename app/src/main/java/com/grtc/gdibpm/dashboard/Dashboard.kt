package com.grtc.gdibpm.dashboard

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "bienes_table")
data class Dashboard(
    @ColumnInfo(name = "id")
    val id:Int,
    @ColumnInfo(name = "nombre" )
    val nombre:String,
    @ColumnInfo(name = "area")
    val area:String,
    @ColumnInfo(name = "estado")
    val estado:String,
    @ColumnInfo (name = "fechaDesplazamiento")
    val fechaDesplazamiento:String


)