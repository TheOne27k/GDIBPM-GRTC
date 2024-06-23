package com.grtc.gdibpm.dashboard

import android.app.Application
import com.grtc.gdibpm.DashboardActivity

class DashboardRepository(application: Application) {
           private val DashboardDAO=PlanningDatabase.getInstance(application).Das
}