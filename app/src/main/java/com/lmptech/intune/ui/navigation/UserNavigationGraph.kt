package com.lmptech.intune.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lmptech.intune.ui.usermenu.UserMenuDestination
import com.lmptech.intune.ui.usermenu.UserMenuScreen


@Composable
fun UserNavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = UserMenuDestination.route) {
        composable(route = UserMenuDestination.route) {
            UserMenuScreen()
        }
    }

}