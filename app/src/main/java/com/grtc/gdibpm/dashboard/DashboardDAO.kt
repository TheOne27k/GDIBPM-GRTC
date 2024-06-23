package com.grtc.gdibpm.dashboard

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface DashboardDAO{
    @Insert
    fun insert()



}