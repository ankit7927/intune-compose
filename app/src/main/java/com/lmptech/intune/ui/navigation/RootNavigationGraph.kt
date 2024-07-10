package com.lmptech.intune.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.lmptech.intune.ui.home.MainScreenDestination
import com.lmptech.intune.ui.home.MainView
import com.lmptech.intune.ui.landing.AuthDestination
import com.lmptech.intune.ui.landing.AuthScreen
import com.lmptech.intune.ui.landing.LandingDestination
import com.lmptech.intune.ui.landing.LandingScreen
import com.lmptech.intune.ui.profile.ProfileDestination
import com.lmptech.intune.ui.profile.ProfileScreen
import com.lmptech.intune.ui.usermenu.UserMenuDestination
import com.lmptech.intune.ui.usermenu.UserMenuScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LandingDestination.route
    ) {

        composable(route = LandingDestination.route) {
            LandingScreen(navigateToAuth = {
                navController.navigate(AuthDestination.route) {
                    popUpTo(LandingDestination.route) {
                        inclusive = true
                    }
                }
            },
                navigateToHome = {
                    navController.navigate(MainScreenDestination.route) {
                        popUpTo(LandingDestination.route) {
                            inclusive = true
                        }
                    }
                })
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
            MainView(onProfileClick = {
                navController.navigate("user")
            })
        }


        navigation(route = "user", startDestination = UserMenuDestination.route) {
            composable(route = UserMenuDestination.route) {
                UserMenuScreen(
                    onBackClick = {
                        navController.navigate(MainScreenDestination.route) {
                            popUpTo(
                                MainScreenDestination.route
                            ) { inclusive = false }
                        }
                    },
                    onProfileClick = {navController.navigate(ProfileDestination.route)},
                    onAccountClick = {},
                    onRequestsClick = {},
                    onLogoutClick = {},
                    onNewRequestClick = {}
                )
            }

            composable(route = ProfileDestination.route) {
                ProfileScreen()
            }
        }

    }

}