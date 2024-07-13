package com.lmptech.intune.ui.landing

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.lmptech.intune.data.local.pref.DataStoreManager
import com.lmptech.intune.ui.navigation.NavDestination

object LandingDestination : NavDestination {
    override val route: String
        get() = "landing_screen"
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    navigateToAuth: () -> Unit = {},
    navigateToHome: () -> Unit = {}
) {
    val dataStoreManager = DataStoreManager.getInstance(LocalContext.current)

    LaunchedEffect(key1 = null) {
        dataStoreManager.isLoggedIn.collect {
            if (it) navigateToHome() else navigateToAuth()
        }
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding(),
    ) {
        CircularProgressIndicator()
    }
}
