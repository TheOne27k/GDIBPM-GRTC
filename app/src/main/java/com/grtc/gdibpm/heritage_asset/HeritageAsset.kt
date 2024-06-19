package com.grtc.gdibpm.heritage_asset

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "heritage_asset_table",
    indices = [Index(value = ["HeritageCode"], unique = true)]
)
data class HeritageAsset (
    val HeritageCode: String,
    val HeritageName: String,
    val HeritageBrand: String,
    val HeritageModel: String,
    val HeritageSerial: String,
    val HeritageColoe: String,
    var HeritageState: HeritageState,
    var HeritageEvidence: Int
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_heritage")
    var HeritageId: Int = 0
}