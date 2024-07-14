package com.lmptech.intune.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lmptech.intune.model.ChatModel

@Composable
fun ChatListItem(chatModel: ChatModel, onChatClick: (String)->Unit = {}) {
    ListItem(
        modifier = Modifier.clickable { onChatClick.invoke(chatModel.id) },
        headlineContent = { Text(text = chatModel.name) },
        leadingContent = {}, supportingContent = { Text(text = chatModel.roomId)})
}