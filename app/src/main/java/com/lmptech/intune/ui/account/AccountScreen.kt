package com.lmptech.intune.ui.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccountScreen(modifier: Modifier = Modifier) {
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
            .padding(horizontal = 12.dp).imePadding()) {

            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Email") },
                value = "", onValueChange = { changed = true }, maxLines = 1
            )

            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Password") },
                value = "", onValueChange = { changed = true }, maxLines = 1
            )
        }
    }
}