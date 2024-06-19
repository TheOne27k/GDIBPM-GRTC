package com.grtc.gdibpm.heritage_asset

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HeritageViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = HeritageRepository(application)

    fun getHeritageAssets() = repository.getHeritageAssets()

    fun insertHeritageAsset(heritageAsset: HeritageAsset) {
        viewModelScope.launch {
            repository.insert(heritageAsset)
        }
    }
}