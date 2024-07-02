package com.grtc.gdibpm.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "lastname")
    val lastname: String,
    @ColumnInfo(name = "telephone_number")
    val telephone_number: String,

    @ColumnInfo(name = "dni")
    val dni: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "contrasenia")
    val contrasenia: String,

    @ColumnInfo(name = "area_ref")
    val areaRef: String,

    @ColumnInfo(name = "role")
    val role:  UserRole
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var userId: Int = 0
}
