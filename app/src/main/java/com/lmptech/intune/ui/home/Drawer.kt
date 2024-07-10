package com.lmptech.intune.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lmptech.intune.data.model.UserModel
import com.lmptech.intune.data.model.response.ChatResponseModel
import com.lmptech.intune.ui.AppViewModelProvider
import com.lmptech.intune.ui.components.ChatListItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    drawerViewModel: DrawerViewModel = viewModel(factory = AppViewModelProvider.factory),
    onChatClick: (String) -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    val drawerState by drawerViewModel.drawerUiState.collectAsState()

    Surface(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(0.8f)
    ) {
        Column(
            modifier = modifier
                .statusBarsPadding()
                .navigationBarsPadding()
                .imePadding()
        ) {

            SearchBar(
                modifier = Modifier.padding(horizontal = 12.dp),
                query = if (drawerState.error != "") drawerState.error else "",
                onQueryChange = {},
                onSearch = {},
                active = false,
                onActiveChange = {}) {}

            if (drawerState.loading) {
                CircularProgressIndicator()
            } else {
                LazyColumn(modifier = Modifier.weight(1f, fill = true)) {
                    items(items = drawerState.chats, key = { it.id }) {
                        ChatListItem(it, onChatClick = onChatClick)
                    }
                }
            }

            if (drawerState.loadingUser) {
                CircularProgressIndicator()
            } else if (drawerState.user != null) {
                ListItem(
                    modifier = Modifier.clickable { onProfileClick.invoke() },
                    headlineContent = { Text(text = drawerState.user!!.name) },
                    supportingContent = { Text(text = drawerState.user!!.username) },
                    trailingContent = {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                    })
            } else {
                Text(text = "failed to get user")
            }
        }


    }
}

