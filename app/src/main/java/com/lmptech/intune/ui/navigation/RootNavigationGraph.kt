package com.lmptech.intune.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lmptech.intune.ui.home.MainScreenDestination
import com.lmptech.intune.ui.home.MainView
import com.lmptech.intune.ui.landing.AuthDestination
import com.lmptech.intune.ui.landing.AuthScreen
import com.lmptech.intune.ui.landing.LandingDestination
import com.lmptech.intune.ui.landing.LandingScreen

@Composable
fun RootNavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AuthChecker.calculateStartDestination()
    ) {

        composable(route = LandingDestination.route) {
            LandingScreen(onGetStartedClick = {navController.navigate(AuthDestination.route)})
        }

        composable(route = AuthDestination.route) {
            AuthScreen(onLoggedIn = {
                navController.navigate(MainScreenDestination.route) {
                    popUpTo(LandingDestination.route) {
                        inclusive = true
                    }
                }
            })
        }

        composable(route = MainScreenDestination.route) {
            MainView()
        }


    }

}