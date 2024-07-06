package com.lmptech.intune.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lmptech.intune.data.model.response.ChatResponseModel

@Composable
fun ChatListItem(chatResponseModel: ChatResponseModel, onChatClick: (String)->Unit = {}) {
    ListItem(
        modifier = Modifier.clickable { onChatClick.invoke(chatResponseModel.id) },
        headlineContent = { Text(text = chatResponseModel.name) },
        leadingContent = {}, supportingContent = { Text(text = chatResponseModel.roomId)})
}