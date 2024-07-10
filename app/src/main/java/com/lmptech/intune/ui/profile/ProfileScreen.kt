package com.lmptech.intune.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lmptech.intune.ui.navigation.NavDestination


object ProfileDestination: NavDestination {
    override val route: String
        get() = "profile_screen"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {

    var changed by remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            TopAppBar(title = { }, actions = {
                TextButton(enabled = changed,
                    onClick = { /*TODO*/ }) {
                    Text(text = "Save")
                }
            })
        }
    ) {
        Column (modifier = Modifier
            .padding(it)
            .padding(horizontal = 12.dp)) {

            // TODO add profile pic

            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Name")},
                value = "", onValueChange = {changed = true}, maxLines = 1)

            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Username")},
                value = "", onValueChange = {changed = true}, maxLines = 1)

            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "About")},
                value = "", onValueChange = {changed = true}, minLines = 4)
        }
    }
}