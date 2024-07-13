package com.lmptech.intune.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.lmptech.intune.IntuneApplication
import com.lmptech.intune.ui.home.DrawerViewModel
import com.lmptech.intune.ui.landing.AuthViewModel
import com.lmptech.intune.ui.profile.ProfileViewModel
import com.lmptech.intune.ui.usermenu.UserMenuViewModel

object AppViewModelProvider {
    val factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            AuthViewModel(application().appContainer.authRepository)
        }

        initializer {
            DrawerViewModel(
                application().appContainer.remoteChatRepository,
                application().appContainer.chatRepository,
                application().appContainer.remoteUserRepository,
                application().appContainer.userRepository
            )
        }

        initializer {
            ProfileViewModel(
                application().appContainer.userRepository,
                application().appContainer.remoteUserRepository
            )
        }

        initializer {
            UserMenuViewModel(
                application().appContainer.userRepository,
                application().appContainer.chatRepository
            )
        }
    }
}

fun CreationExtras.application () : IntuneApplication = (this[APPLICATION_KEY] as IntuneApplication)