package com.lmptech.intune.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.lmptech.intune.IntuneApplication
import com.lmptech.intune.ui.landing.AuthViewModel

object AppViewModelProvider {
    val factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            AuthViewModel(application().appContainer.authRepository)
        }
    }
}

fun CreationExtras.application () : IntuneApplication = (this[APPLICATION_KEY] as IntuneApplication)