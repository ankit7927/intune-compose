package com.lmptech.intune.ui.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import com.lmptech.intune.domain.pref.DataStoreManager
import com.lmptech.intune.ui.navigation.NavDestination
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.stateIn

object LandingDestination : NavDestination {
    override val route: String
        get() = "landing_screen"
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LandingScreen(modifier: Modifier = Modifier, navigateToAuth: () -> Unit = {}, navigateToHome: () -> Unit = {}) {
    val dataStoreManager = DataStoreManager.getInstance(LocalContext.current)
    val loggedIn = dataStoreManager.isUserLoggedIn().collectAsState(initial = null)

    if (loggedIn.value != null && loggedIn.value!!) {
        navigateToHome()
    } else if (loggedIn.value == false) {
        navigateToAuth()
    } else {
        Box (modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
