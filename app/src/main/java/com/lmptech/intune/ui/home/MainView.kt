package com.lmptech.intune.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.lmptech.intune.ui.navigation.NavDestination
import kotlinx.coroutines.launch

object MainScreenDestination : NavDestination {
    override val route: String
        get() = "main_screen"

}

@Composable
fun MainView(
    onProfileClick:() ->Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    val dState = rememberDrawerState(DrawerValue.Closed)

    if (dState.isAnimationRunning) {
        keyboardController?.hide()
    }


    var activeChatId by remember {
        mutableStateOf("")
    }

    // TODO create pushing drawer

    BackHandler(enabled = dState.isOpen) {
        if (dState.isOpen) {
            coroutineScope.launch {
                dState.close()
            }
        }
    }

    fun onChatClick(chatId: String) {
        coroutineScope.launch {
            dState.close()
            activeChatId = chatId
        }
    }


    ModalNavigationDrawer(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)
            .imePadding(),
        drawerState = dState,
        gesturesEnabled = true,
        drawerContent = {
            Drawer(
                onChatClick = { onChatClick(it) },
                onProfileClick = onProfileClick
            )
        }) {
        HomeScreen(activeChatId = activeChatId)
    }
}