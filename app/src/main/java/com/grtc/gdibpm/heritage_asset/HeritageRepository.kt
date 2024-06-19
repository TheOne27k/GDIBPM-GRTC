package com.grtc.gdibpm.heritage_asset

import android.app.Application
import androidx.lifecycle.LiveData
import com.grtc.gdibpm.database.GrtcDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class HeritageRepository(application: Application) {
    private val heritageDao = GrtcDatabase.getInstance(application).heritageDao()
    fun getHeritageAssets() : LiveData<List<HeritageAsset>>{
        return heritageDao.list()
    }
    suspend fun insert(heritageAsset: HeritageAsset){
        withContext(Dispatchers.IO){
            heritageDao.insert(heritageAsset)
        }
    }
}