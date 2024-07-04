package com.lmptech.intune.ui.usermenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserMenuScreen(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Menu") })
    }, floatingActionButton = {
        FloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }) {
        Column(modifier = Modifier.padding(it)) {
            ListItem(leadingContent = {
                Icon(
                    imageVector = Icons.Default.Person, contentDescription = ""
                )
            }, headlineContent = { Text(text = "Profile") }, supportingContent = {
                Text(text = "manage your profile related settings")
            }, modifier = Modifier.clickable { })

            ListItem(leadingContent = {
                Icon(
                    imageVector = Icons.Default.AccountBox, contentDescription = ""
                )
            }, headlineContent = { Text(text = "Account") }, supportingContent = {
                Text(text = "manage your account related settings")
            }, modifier = Modifier.clickable { })

            ListItem(leadingContent = {
                Icon(
                    imageVector = Icons.Default.Email, contentDescription = ""
                )
            }, headlineContent = { Text(text = "Requests") }, supportingContent = {
                Text(text = "manage chat requests")
            }, modifier = Modifier.clickable { })
        }
    }
}
