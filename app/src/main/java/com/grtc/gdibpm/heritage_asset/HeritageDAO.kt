package com.grtc.gdibpm.heritage_asset

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface HeritageDAO {
    @Insert
    fun insert(heritage: HeritageAsset)
    @Update
    fun update(heritage: HeritageAsset)
    @Delete
    fun delete(heritage: HeritageAsset)
    @Query("SELECT * FROM heritage_asset_table")
    fun list(): LiveData<List<HeritageAsset>>
}