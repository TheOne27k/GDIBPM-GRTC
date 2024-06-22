package com.grtc.gdibpm.displacement

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DisplacementViewModel(application: Application): AndroidViewModel(application){
    private val repository = DisplacementRepository(application)

    fun getDisplacements() = repository.getDisplacements()

    fun insertDisplacement(displacement: Displacement) {
        viewModelScope.launch {
            repository.insert(displacement)
        }
    }
}