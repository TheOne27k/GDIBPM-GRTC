package com.grtc.gdibpm.displacement

import com.google.firebase.firestore.DocumentReference
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Displacement(
    val sender: DocumentReference,
    val receiver: DocumentReference,
    val motive: String,
    val listHeritage: List<DocumentReference?>,
    val date: String = getCurrentDate(),
    val state: DisplacementStatus = DisplacementStatus.IN_PROCESS,
    val isExpanded: Boolean = false
) {
    companion object {
        fun getCurrentDate(): String {
            return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        }
    }
}
