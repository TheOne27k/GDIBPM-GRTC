package com.grtc.gdibpm.users

import android.app.Application
import androidx.lifecycle.LiveData
import com.grtc.gdibpm.database.GrtcDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(application: Application) {

    private val userDao = GrtcDatabase.getInstance(application).userDao()

    fun getUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }

    suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.insertUser(user)
        }
    }
}
