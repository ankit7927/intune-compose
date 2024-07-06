package com.lmptech.intune.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.lmptech.intune.ui.navigation.RootNavigationGraph
import com.lmptech.intune.ui.theme.IntuneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            IntuneTheme {
                RootNavigationGraph()
            }
        }
    }
}
