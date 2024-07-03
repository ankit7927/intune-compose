package com.lmptech.intune.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lmptech.intune.domain.models.DrawerState
import com.lmptech.intune.domain.models.opposite
import com.lmptech.intune.ui.components.ChatListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    onNavigationMenuClick: (DrawerState) -> Unit
) {
    Scaffold(modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = "Chat name") }, navigationIcon = {
                IconButton(onClick = { onNavigationMenuClick(drawerState.opposite()) }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                }
            })
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = 12.dp)
        ) {

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(count = 20) {
                    ChatListItem()
                }
            }

            Column {
                BasicTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(MaterialTheme.colorScheme.surface),
                    maxLines = 3,
                    decorationBox = {

                        it()
                    }
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "")
                    }

                    FilledTonalButton(onClick = { /*TODO*/ }) {
                        Text(text = "Send")
                    }
                }
            }
        }
    }
}
