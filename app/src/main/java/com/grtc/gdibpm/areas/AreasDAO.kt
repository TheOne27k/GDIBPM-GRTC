package com.grtc.gdibpm.users

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.grtc.gdibpm.areas.Areas

@Dao
interface AreasDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAreas(areas: Areas)

    @Update
    fun updateAreas(areas: Areas)

    @Delete
    fun deleteAreas(areas: Areas)

    @Query("SELECT * FROM areas WHERE areas_id = :areasId")
    fun getAreas(areasId: Int): Areas?

    @Query("SELECT * FROM areas")
    fun getAllAreas(): LiveData<List<Areas>>

}