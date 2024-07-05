package com.lmptech.intune.ui.navigation

import com.lmptech.intune.ui.home.MainScreenDestination
import com.lmptech.intune.ui.landing.LandingDestination

interface NavDestination {
    val route:String
}

object AuthChecker {
    fun calculateStartDestination() :String {
        // here calculate user authenticated or not and return route
        return MainScreenDestination.route
    }
}