package com.lmptech.intune.ui.navigation

import android.content.Context
import com.lmptech.intune.data.pref.PreferenceManager
import com.lmptech.intune.ui.home.MainScreenDestination
import com.lmptech.intune.ui.landing.LandingDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

interface NavDestination {
    val route:String
}

object AuthChecker {
    fun calculateStartDestination(context: Context) :String {
        if (PreferenceManager.isTokenExists(context))
            return MainScreenDestination.route
        return LandingDestination.route
    }
}