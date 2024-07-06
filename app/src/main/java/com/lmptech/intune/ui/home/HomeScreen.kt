package com.lmptech.intune.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    activeChatId: String,
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit = {},
) {

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ListItem(
                headlineContent = { Text(text = activeChatId) },
                supportingContent = { Text(text = "some test") },
                leadingContent = {
                    IconButton(onClick = { openDrawer.invoke() }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                    }
                })

            LazyColumn (modifier = Modifier.weight(1f, fill = true)) {
                items(count = 30) {
                    Text(text = "text`")
                }
            }

            OutlinedTextField(value = "", onValueChange = {})
        }
    }

}
