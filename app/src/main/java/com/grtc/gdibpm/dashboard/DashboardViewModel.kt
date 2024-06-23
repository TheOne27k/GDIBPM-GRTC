package com.grtc.gdibpm.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class DashboardViewModel(application: Application):AndroidViewModel(application) {

    private val repository= DashboardRepository(application)
}