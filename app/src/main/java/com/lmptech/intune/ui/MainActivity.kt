package com.lmptech.intune.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lmptech.intune.ui.home.MainView
import com.lmptech.intune.ui.theme.IntuneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntuneTheme {
                MainView()
            }
        }
    }
}
