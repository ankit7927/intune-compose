package com.lmptech.intune.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.lmptech.intune.ui.components.ChatListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer() {
    Column (
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(fraction = 0.73f)
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        SearchBar(query = "",
            onQueryChange = {}, onSearch = {},
            active = false, onActiveChange = {},
            content = {}, trailingIcon = { IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
            }})
        

        LazyColumn(modifier = Modifier.weight(0.8f)
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(12.dp))) {
            items(count = 30) {
                ChatListItem()
            }
        }

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            onClick = { /*TODO*/ }) {
            ListItem(
                headlineContent = { Text(text = "User name") },
                leadingContent = { Text(text = "A") },
                supportingContent = { Text(text = "last active") })
        }

    }

}