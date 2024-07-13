package com.lmptech.intune.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lmptech.intune.ui.AppViewModelProvider
import com.lmptech.intune.ui.navigation.NavDestination


object ProfileDestination: NavDestination {
    override val route: String
        get() = "profile_screen"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.factory)
) {

    val profileState by profileViewModel.profile.collectAsState()


    Scaffold (
        topBar = {
            TopAppBar(title = { }, actions = {
                TextButton(enabled = profileState.edited,
                    onClick = { profileViewModel.onSave() }) {
                    Text(text = "Save")
                }
            })
        }
    ) { paddingValues ->
        Column (modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 12.dp)) {

            // TODO add profile pic

            if (profileState.userModel != null && !profileState.loading) {

                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Name") },
                    value = profileState.userModel!!.name, onValueChange = {
                        profileViewModel.onEdit()
                        profileViewModel.onNameChange(it)
                    }, maxLines = 1
                )

                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Username") },
                    value = profileState.userModel!!.username, onValueChange = {
                        profileViewModel.onEdit()
                        profileViewModel.onUsernameChange(it)
                    }, maxLines = 1
                )

                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "About") },
                    value = "", onValueChange = {
                        profileViewModel.onEdit()
                        profileViewModel.onBioChange(it)
                    }, minLines = 4
                )
            } else {
                CircularProgressIndicator()
            }
        }
    }
}