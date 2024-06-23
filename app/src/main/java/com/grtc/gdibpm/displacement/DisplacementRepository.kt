package com.grtc.gdibpm.displacement

import android.app.Application
import androidx.lifecycle.LiveData
import com.grtc.gdibpm.database.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DisplacementRepository(application: Application) {

    private val displacementDAO = database.getInstance(application).displacementDAO()

    fun getDisplacements(): LiveData<List<Displacement>>{
        return displacementDAO.list()
    }

    suspend fun insert(displacement: Displacement){
withContext(Dispatchers.IO){
    displacementDAO.insert(displacement)
}
    }

}