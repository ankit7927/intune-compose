package com.lmptech.intune.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileSheet() {
    val coroutineScope = rememberCoroutineScope()
    val showSheet by remember { mutableStateOf(true) }
    val sheetState = rememberModalBottomSheetState()


    ModalBottomSheet(
        sheetState = sheetState,
        modifier = Modifier.padding(horizontal = 12.dp),
        onDismissRequest = { /*TODO*/ }) {
        Column (modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),) {
            Row (modifier = Modifier.fillMaxWidth()) {
                Image(
                    modifier = Modifier
                        .size(70.dp)
                        .background(color = Color.LightGray)
                        .clip(
                            RoundedCornerShape(16.dp)
                        ),
                    imageVector = Icons.Default.Person, contentDescription = "")
                Spacer(modifier = Modifier.width(12.dp))
                Column (modifier = Modifier.padding(vertical = 4.dp))  {
                    Text(text = "full name", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
                    Text(text = "username")
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row  (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                FilledTonalButton(onClick = { /*TODO*/ }) {
                    Text(text = "add")
                }
                FilledTonalButton(onClick = { /*TODO*/ }) {
                    Text(text = "Cancel")
                }
                FilledTonalButton(onClick = { /*TODO*/ }) {
                    Text(text = "Accept")
                }
                FilledTonalButton(onClick = { /*TODO*/ }) {
                    Text(text = "Remove")
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Box (modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(12.dp))) {
                Text(modifier = Modifier.padding(12.dp),
                    text = "this will be the bio for the user account")
            }

        }
    }
}
