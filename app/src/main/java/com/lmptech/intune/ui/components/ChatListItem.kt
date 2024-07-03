package com.lmptech.intune.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ChatListItem() {
    ListItem(
        modifier = Modifier.clickable {  },
        headlineContent = { Text(text = "Chat title") },
        leadingContent = { Text(text = "A") }, supportingContent = { Text(text = "last active ")})
}