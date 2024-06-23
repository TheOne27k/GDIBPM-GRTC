package com.grtc.gdibpm.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(application: Application):
    AndroidViewModel(application) {

        private val repository=UserRepository(application)
    fun getUsers():LiveData<List<User>>{
        return repository.getUsers()
    }
    fun insertUser(user: User){
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

}