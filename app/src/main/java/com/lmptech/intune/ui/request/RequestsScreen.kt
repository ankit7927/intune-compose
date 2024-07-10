package com.lmptech.intune.ui.request

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lmptech.intune.ui.navigation.NavDestination

object RequestsDestination: NavDestination {
    override val route: String
        get() = "request_screen"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RequestsScreen() {
    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = "Requests") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "")
            }
        }
    ) {
        LazyColumn (modifier = Modifier
            .padding(it)
            .padding(horizontal = 12.dp)) {
            items(count = 12) {
                ListItem(
                    leadingContent = { Icon(imageVector = Icons.Default.Person, contentDescription = "")},
                    headlineContent = { Text(text = "request from $it") },
                    supportingContent = { Text(text = "username")},
                    trailingContent = {
                        Row {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Default.Check, contentDescription = "")
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Default.Close, contentDescription = "")
                            }
                        }
                    })
            }
        }
    }
}