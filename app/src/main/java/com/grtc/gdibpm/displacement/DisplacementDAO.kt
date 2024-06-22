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

    //Filtrado por fechas
    @Query ("SELECT * FROM displacement_table WHERE date_displacement = :date")
    fun listByDate(date: String): LiveData<List<Displacement>>

    //Filtrado por estado
    @Query ("SELECT * FROM displacement_table WHERE estado ")
    fun listByStatus(): LiveData<List<Displacement>>

    //Filtrado por remitente
    @Query ("SELECT * FROM displacement_table WHERE Remitente_displacement = :string")
    fun listByRemitente(string: String): LiveData<List<Displacement>>

}
