package com.grtc.gdibpm.heritage_asset

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "heritage_asset_table",
    indices = [Index(value = ["heritage_code"], unique = true)]
)
data class HeritageAsset (
    @ColumnInfo(name = "heritage_code")
    val HeritageCode: String,
    @ColumnInfo(name = "heritage_name")
    val HeritageName: String,
    @ColumnInfo(name = "heritage_brand")
    val HeritageBrand: String,
    @ColumnInfo(name = "heritage_model")
    val HeritageModel: String,
    @ColumnInfo(name = "heritage_serial")
    val HeritageSerial: String,
    @ColumnInfo(name = "heritage_color")
    val HeritageColor: String,
    @ColumnInfo(name = "heritage_state")
    var HeritageState: HeritageState,
    @ColumnInfo(name = "heritage_evidence")
    var HeritageEvidence: String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_heritage")
    var HeritageId: Int = 0
}