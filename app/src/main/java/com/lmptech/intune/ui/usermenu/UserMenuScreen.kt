package com.lmptech.intune.ui.usermenu

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lmptech.intune.data.local.pref.DataStoreManager
import com.lmptech.intune.ui.AppViewModelProvider
import com.lmptech.intune.ui.navigation.NavDestination
import kotlinx.coroutines.launch

object UserMenuDestination: NavDestination {
    override val route: String
        get() = "user_menu_screen"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserMenuScreen(
    userMenuViewModel: UserMenuViewModel = viewModel(factory = AppViewModelProvider.factory),
    onBackClick: () -> Unit,
    onProfileClick: () -> Unit,
    onAccountClick: () -> Unit,
    onRequestsClick: () -> Unit,
    onNewRequestClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }
    val sheetState: SheetState = rememberModalBottomSheetState()

    val dataStoreManager = DataStoreManager.getInstance(LocalContext.current)

    BackHandler {
        onBackClick()
    }

    LaunchedEffect(key1 = userMenuViewModel.loggedOut) {
        if (userMenuViewModel.loggedOut) {
            println("clearing pref")
            dataStoreManager.clearToken()
            onLogoutClick.invoke()
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Menu") })
    }, floatingActionButton = {
        FloatingActionButton(onClick = { onNewRequestClick.invoke() }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }) {
        Column(modifier = Modifier.padding(it)) {
            ListItem(leadingContent = {
                Icon(
                    imageVector = Icons.Default.Person, contentDescription = ""
                )
            }, headlineContent = { Text(text = "Profile") }, supportingContent = {
                Text(text = "manage your profile related settings")
            }, modifier = Modifier.clickable { onProfileClick.invoke() })

            ListItem(leadingContent = {
                Icon(
                    imageVector = Icons.Default.AccountBox, contentDescription = ""
                )
            }, headlineContent = { Text(text = "Account") }, supportingContent = {
                Text(text = "manage your account related settings")
            }, modifier = Modifier.clickable { onAccountClick.invoke() })

            ListItem(leadingContent = {
                Icon(
                    imageVector = Icons.Default.Email, contentDescription = ""
                )
            }, headlineContent = { Text(text = "Requests") }, supportingContent = {
                Text(text = "manage chat requests")
            }, modifier = Modifier.clickable { onRequestsClick.invoke() })

            ListItem(leadingContent = {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ExitToApp, contentDescription = ""
                )
            }, headlineContent = { Text(text = "Logout") }, supportingContent = {
                Text(text = "logout and clear all data")
            }, modifier = Modifier.clickable {
                showSheet = true
                coroutineScope.launch {
                    sheetState.show()
                }
            })
        }

        if (showSheet) {
            ModalBottomSheet(
                modifier = Modifier.navigationBarsPadding(),
                onDismissRequest = {
                showSheet = false
                coroutineScope.launch {
                    sheetState.hide()
                }
            }) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp)) {
                    Text(text = "are u sure to logout", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { userMenuViewModel.logout()}) {
                        Text(text = "Yes. logout")
                    }
                }

            }
        }

    }
}
