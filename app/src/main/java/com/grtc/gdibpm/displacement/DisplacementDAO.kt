package com.grtc.gdibpm.displacement

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DisplacementDAO {

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert
    fun insert(displacement: Displacement)

    @Update
    fun update(displacement: Displacement)

    @Delete
    fun delete(displacement: Displacement)

    @Query("SELECT * FROM displacement_table")
    fun list(): LiveData<List<Displacement>>

    @Query ("SELECT * FROM displacement_table WHERE date_displacement = :date")
    fun listByDate(date: String): LiveData<List<Displacement>>

    @Query ("SELECT * FROM displacement_table WHERE motivo = :motivo")
    fun listByMotivo(motivo: String): LiveData<List<Displacement>>

    @Query ("SELECT * FROM displacement_table WHERE estado = :status")
    fun listByStatusSuccess(status: DisplacementStatus = DisplacementStatus.SUCCESS): LiveData<List<Displacement>>

    @Query ("SELECT * FROM displacement_table WHERE estado = :status")
    fun listByStatusInProcess(status: DisplacementStatus = DisplacementStatus.IN_PROCESS): LiveData<List<Displacement>>

    @Query ("SELECT * FROM displacement_table WHERE estado = :status")
    fun listByStatusCancel(status: DisplacementStatus = DisplacementStatus.CANCEL): LiveData<List<Displacement>>


}
