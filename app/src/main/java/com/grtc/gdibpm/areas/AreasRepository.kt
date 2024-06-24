package com.grtc.gdibpm.areas

import android.app.Application
import androidx.lifecycle.LiveData
import com.grtc.gdibpm.database.GrtcDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AreasRepository(application: Application) {

    private val AreasDAO = GrtcDatabase.getInstance(application).areasDAO()

    fun getAreas(): LiveData<List<Areas>> {
        return AreasDAO.getAllAreas()
    }

    suspend fun insertAreas(areas: Areas) {
        withContext(Dispatchers.IO) {
            AreasDAO.insertAreas(areas)
        }
    }
}