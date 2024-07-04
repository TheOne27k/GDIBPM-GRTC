package com.grtc.gdibpm.management.user

import com.google.firebase.firestore.DocumentReference

data class User(
    val id: String,
    val name: String,
    val lastname: String,
    val telephone_number: String,
    val dni: String,
    val email: String,
    val areaRef: DocumentReference,
    val areaNane: String,
    val role: UserRole
)
