package com.lmptech.intune.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.lmptech.intune.domain.models.DrawerState
import com.lmptech.intune.domain.models.isOpened
import kotlin.math.roundToInt

@Composable
fun MainView() {
    var drawerState by remember { mutableStateOf(DrawerState.CLOSED) }


    val configuration = LocalConfiguration.current

    val offsetValue by remember {
        derivedStateOf { ((configuration.screenWidthDp / 4) * 3).dp}
    }

    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState.isOpened()) offsetValue else 0.dp,
        label = "Animated Offset"
    )

    val animatedShadow by animateDpAsState(
        targetValue = if (drawerState.isOpened()) 12.dp else 0.dp,
        label = "Animated Offset"
    )


    BackHandler(enabled = drawerState.isOpened()) {
        drawerState = DrawerState.CLOSED
    }


    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        Drawer()
        HomeScreen(modifier = Modifier
            .offset(x = animatedOffset),
            drawerState = drawerState,
            onNavigationMenuClick = { drawerState = it })
    }
}