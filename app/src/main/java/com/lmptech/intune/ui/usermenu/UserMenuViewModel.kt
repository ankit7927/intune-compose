package com.lmptech.intune.ui.usermenu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmptech.intune.data.local.database.repository.ChatRepository
import com.lmptech.intune.data.local.database.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserMenuViewModel(
    private val userRepository: UserRepository,
    private val chatRepository: ChatRepository
) : ViewModel() {
    var loggedOut by mutableStateOf(false)

    fun logout() {
        viewModelScope.launch (Dispatchers.IO) {
            userRepository.deleteUser()
            chatRepository.deleteAllChats()
        }.invokeOnCompletion { loggedOut = true }
    }
}