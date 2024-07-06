package com.lmptech.intune.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.lmptech.intune.data.model.response.ChatResponseModel
import com.lmptech.intune.ui.AppViewModelProvider
import com.lmptech.intune.ui.components.ChatListItem


@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    drawerViewModel: DrawerViewModel = viewModel(factory = AppViewModelProvider.factory),
    onChatClick:(String) -> Unit = {}
) {
    val drawerState by drawerViewModel.drawerUiState.collectAsState()

    Surface(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(0.8f)
    ) {
        if (drawerState.loading) {
            CircularProgressIndicator()
        } else if (drawerState.error != "") {
            Text(text = drawerState.error)
        } else {
            DrawerBody(chats = drawerState.chats, onChatClick = onChatClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerBody(modifier: Modifier = Modifier, chats: List<ChatResponseModel>, onChatClick: (String) -> Unit = {}) {

    Column(modifier = modifier
        .statusBarsPadding()
        .navigationBarsPadding()
        .imePadding()){
        SearchBar(
            modifier = Modifier.padding(horizontal = 12.dp),
            query = "", onQueryChange = {}, onSearch = {}, active = false, onActiveChange = {}) {}
        LazyColumn(modifier = Modifier.weight(1f, fill = true)) {
            items(items = chats, key = {it.id}) {
                ChatListItem(it, onChatClick = onChatClick)
            }
        }

        ListItem(
            headlineContent = { Text(text = "User Name") },
            supportingContent = { Text(text = "status") },
            trailingContent = {
                IconButton(
                    onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "")

                }
            })
    }
}
