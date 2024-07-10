package com.lmptech.intune.ui.request

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lmptech.intune.ui.navigation.NavDestination

object NewRequestDestination: NavDestination {
    override val route: String
        get() = "new_request_screen"
}

@Composable
fun NewRequestScreen(modifier: Modifier = Modifier) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(horizontal = 12.dp)
                .imePadding()
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Add chat request",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "you can request other person for chat by there username")

            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "username"
                    )
                })

            Spacer(modifier = Modifier.height(12.dp))
            Button(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
                Text(text = "Send Request")
            }
        }
    }
}

