package com.lmptech.intune.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lmptech.intune.domain.models.DrawerState
import com.lmptech.intune.domain.models.opposite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, drawerState: DrawerState, onNavigationMenuClick: (DrawerState) -> Unit) {
    Scaffold(modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = "Chat name") }, navigationIcon = {
                IconButton(onClick = { onNavigationMenuClick(drawerState.opposite()) }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                }
            })
        }) {
        Box(modifier = Modifier.padding(it)) {
            Text(text = "Home")
        }
    }
}