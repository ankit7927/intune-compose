package com.lmptech.intune.ui.landing

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AuthScreen(modifier: Modifier = Modifier) {

    var login by remember {
        mutableStateOf(true)
    }

    Column (modifier = modifier
        .fillMaxSize()
        .padding(16.dp)
        .imePadding()) {

        Text(text = if (login) "Welcome Back" else "Hello there!", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium))
        Text(text = if (login) "we are excited to see you again" else "thanks for choosing us.")
        Spacer(modifier = Modifier.height(18.dp))

        AnimatedVisibility(visible = !login) {
            Column {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "", onValueChange = {}, label = { Text(text = "Name")})

                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "", onValueChange = {}, label = { Text(text = "Username")})
            }


        }

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "", onValueChange = {}, label = { Text(text = "Email")})

        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "", onValueChange = {}, label = { Text(text = "Password")})


        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }) {
            Text(text = "Continue")
        }


        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { login = !login }) {
            Text(text = if (login) "Register" else "Login")
        }
    }
}
