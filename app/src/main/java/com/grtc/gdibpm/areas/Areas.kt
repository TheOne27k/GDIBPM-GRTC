package com.grtc.gdibpm.areas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "areas")
data class Areas(
    @ColumnInfo(name = "nombre_area")
    val nombre: String,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "areas_id")
    var areasId: Int = 0
}
