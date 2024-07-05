package com.lmptech.intune.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.lmptech.intune.domain.models.DrawerState
import com.lmptech.intune.domain.models.isOpened
import com.lmptech.intune.ui.navigation.NavDestination
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.roundToInt

object MainScreenDestination : NavDestination {
    override val route: String
        get() = "main_screen"

}

@Composable
fun MainView() {
    val coroutineScope = rememberCoroutineScope()
    val dState  = rememberDrawerState(DrawerValue.Closed)

    // TODO create pushing drawer

    BackHandler(enabled = dState.isOpen) {
        if (dState.isOpen) {
            coroutineScope.launch {
                dState.close()
            }
        }
    }


    ModalNavigationDrawer(
        drawerState = dState,
        gesturesEnabled = true,
        drawerContent = { Drawer() }) {
        HomeScreen(modifier = Modifier)
    }


}