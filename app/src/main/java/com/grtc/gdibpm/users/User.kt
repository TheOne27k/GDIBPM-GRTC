package com.grtc.gdibpm.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @ColumnInfo(name = "nombre")
    val nombre: String,

    @ColumnInfo(name = "apellido_paterno")
    val apellidoPaterno: String,

    @ColumnInfo(name = "apellido_materno")
    val apellidoMaterno: String,

    @ColumnInfo(name = "correo")
    val correo: String,

    @ColumnInfo(name = "dni")
    val dni: String,

    @ColumnInfo(name = "clave_secreta")
    val claveSecreta: String,

    @ColumnInfo(name = "contrasenia")
    val contrasenia: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var userId: Int = 0
}
