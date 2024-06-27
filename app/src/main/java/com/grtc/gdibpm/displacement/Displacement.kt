package com.grtc.gdibpm.displacement

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "displacement_table")
data class Displacement (

    @ColumnInfo(name = "Remitente_displacement")
    val remitente: String,

    @ColumnInfo(name = "Receptor_displacement")
    val receptor: String,

    @ColumnInfo(name = "motivo")
    val motivo: String,

    @ColumnInfo(name = "date_displacement")
    val fecha: String,

    @ColumnInfo(name = "estado")
    val estado: DisplacementeStatus,
    val isExpanded: Boolean = false

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_displacement")
    var displacementId: Int = 0

}