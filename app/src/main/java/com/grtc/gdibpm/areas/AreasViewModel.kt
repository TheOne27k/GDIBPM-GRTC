package com.grtc.gdibpm.areas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AreasViewModel (application: Application):
    AndroidViewModel(application){

    private val repository= AreasRepository(application)
    fun getAreas(): LiveData<List<Areas>> {
        return repository.getAreas()
    }
    fun insertUser(areas: Areas){
        viewModelScope.launch {
            repository.insertAreas(areas)
        }
    }
}