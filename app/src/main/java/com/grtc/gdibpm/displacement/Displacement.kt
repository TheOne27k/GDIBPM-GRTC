package com.grtc.gdibpm.displacement

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "displacement_table")
data class Displacement(
    @ColumnInfo(name = "Remitente_displacement")
    val remitente: String,

    @ColumnInfo(name = "Receptor_displacement")
    val receptor: String,

    @ColumnInfo(name = "motivo")
    val motivo: String,

    @ColumnInfo(name = "date_displacement")
    val fecha: String = getCurrentDate(),

    @ColumnInfo(name = "estado")
    val estado: DisplacementStatus,

    @ColumnInfo(name = "is_expanded")
    val isExpanded: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_displacement")
    var displacementId: Int = 0

    companion object {
        fun getCurrentDate(): String {
            return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        }
    }
}
