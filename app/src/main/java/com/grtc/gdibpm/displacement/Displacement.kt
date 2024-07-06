package com.grtc.gdibpm.displacement

import com.google.firebase.firestore.DocumentReference
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Displacement(
    val id: String,
    val sender: DocumentReference,
    val receiver: DocumentReference,
    val motive: String,
    val listHeritage: List<DocumentReference?>,
    val date: String = getCurrentDate(),
    var state: DisplacementStatus = DisplacementStatus.IN_PROCESS,
    var senderName: String = "",
    var receiverName: String = "",
    var isExpanded: Boolean = false
) {
    companion object {
        fun getCurrentDate(): String {
            return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        }
    }
}
