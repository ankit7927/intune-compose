package com.lmptech.intune.ui.landing

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lmptech.intune.domain.pref.PreferenceManager
import com.lmptech.intune.ui.AppViewModelProvider
import com.lmptech.intune.ui.navigation.NavDestination
import kotlinx.coroutines.launch

object AuthDestination : NavDestination {
    override val route: String
        get() = "auth_screen"
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = viewModel(factory = AppViewModelProvider.factory),
    onLoggedIn: () -> Unit = {}
) {
    val coroutine = rememberCoroutineScope()
    val uiState by authViewModel.uiState.collectAsState()
    val current = LocalContext.current

    LaunchedEffect(key1 = uiState.token) {
        if (uiState.token != null) {
            authViewModel.savingToken()
            coroutine.launch {
                PreferenceManager.saveToken(current, uiState.token!!)
                onLoggedIn.invoke()
            }
        }
    }

    Column (modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
        .statusBarsPadding()
        .navigationBarsPadding()
        .imePadding()) {

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = if (uiState.loginScreen) "Welcome Back" else "Hello there!", style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Medium))
        Text(text = if (uiState.loginScreen) "we are excited to see you again" else "thanks for choosing us.")

        AnimatedVisibility(visible = uiState.error != "")  {
            Text(text = uiState.error, color = Color.Red)
        }

        AnimatedVisibility(visible = !uiState.loginScreen) {
            Column {
                Spacer(modifier = Modifier.height(14.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.name, onValueChange = {authViewModel.onNameChange(it)}, label = { Text(text = "Name")})

                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.username, onValueChange = {authViewModel.onUsernameChange(it)}, label = { Text(text = "Username")})
            }


        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.email, onValueChange = {authViewModel.onEmailChange(it)}, label = { Text(text = "Email")})

        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.password, onValueChange = {authViewModel.onPasswordChange(it)}, label = { Text(text = "Password")})


        Spacer(modifier = Modifier.height(16.dp))
        Button(
            enabled = ! (uiState.isLoading),
            modifier = Modifier.fillMaxWidth(),
            onClick = { authViewModel.onContinueClick() }) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text(text = "Continue")
            }
        }


        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            enabled = ! (uiState.isLoading),
            modifier = Modifier.fillMaxWidth(),
            onClick = { authViewModel.toggleAuthScreen() }) {
            Text(text = if (uiState.loginScreen) "Register" else "Login")
        }
    }
}
