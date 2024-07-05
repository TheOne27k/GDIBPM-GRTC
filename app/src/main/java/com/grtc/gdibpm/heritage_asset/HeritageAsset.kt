package com.grtc.gdibpm.heritage_asset

import com.google.firebase.firestore.DocumentReference

data class HeritageAsset (
    val HeritageCode: String,
    val HeritageName: String,
    val HeritageBrand: String,
    val HeritageModel: String,
    val HeritageSerial: String,
    val HeritageColor: String,
    var HeritageState: HeritageState,
    var HeritageEvidence: String,
    val documentReference: DocumentReference? = null
)