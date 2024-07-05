package com.lmptech.intune.ui.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import com.lmptech.intune.ui.navigation.NavDestination

object LandingDestination : NavDestination {
    override val route: String
        get() = "landing_screen"
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LandingScreen(modifier: Modifier = Modifier, onGetStartedClick: () -> Unit = {}) {
    Box (modifier = modifier.fillMaxSize().navigationBarsPadding(), contentAlignment = Alignment.BottomCenter) {
        Button(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            onClick = onGetStartedClick) {
            Text(text = "Get Started")
        }
    }
}
