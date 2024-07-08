package com.lmptech.intune.ui.navigation

import android.content.Context
import com.lmptech.intune.data.pref.DataStoreManager
import com.lmptech.intune.ui.home.MainScreenDestination
import com.lmptech.intune.ui.landing.LandingDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

interface NavDestination {
    val route:String
}
