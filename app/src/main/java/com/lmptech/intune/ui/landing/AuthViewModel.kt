package com.lmptech.intune.ui.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmptech.intune.data.model.LoginRequest
import com.lmptech.intune.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AuthUiState (
    val name:String = "",
    val username:String = "",
    val email:String = "",
    val password:String = "",
    val loggedIn:Boolean = false,
    val isLoading:Boolean = false,
    val error:String = "",
    var loginScreen: Boolean = true,
)

class AuthViewModel (private val authRepository: AuthRepository) : ViewModel() {
    private val uiStateFlow: MutableStateFlow<AuthUiState> = MutableStateFlow(AuthUiState())

    val uiState: StateFlow<AuthUiState>
        get() = uiStateFlow

    fun toggleAuthScreen() {
        viewModelScope.launch {
            uiStateFlow.emit(
                uiStateFlow.value.copy(
                    loginScreen = !uiStateFlow.value.loginScreen,
                    name = "",
                    username = ""
                )
            )
        }
    }

    fun onNameChange(name:String) {
        viewModelScope.launch {
            uiStateFlow.emit(uiStateFlow.value.copy(name = name))
        }
    }

    fun onUsernameChange(username:String) {
        viewModelScope.launch {
            uiStateFlow.emit(uiStateFlow.value.copy(username = username))
        }
    }

    fun onEmailChange(email:String) {
        viewModelScope.launch {
            uiStateFlow.emit(uiStateFlow.value.copy(email = email))
        }
    }

    fun onPasswordChange(password:String) {
        viewModelScope.launch {
            uiStateFlow.emit(uiStateFlow.value.copy(password = password))
        }
    }

    fun onContinueClick() {
        viewModelScope.launch {
            uiStateFlow.emit(uiStateFlow.value.copy(isLoading = true))
            try {
                val response = authRepository.login(LoginRequest(uiState.value.email, uiState.value.password))
                if (response.isSuccessful) {
                    // save token hr

                    uiStateFlow.emit(uiStateFlow.value.copy(isLoading = false, loggedIn = true))
                } else {
                    println(response.toString())
                    uiStateFlow.emit(
                        uiStateFlow.value.copy(
                            isLoading = false,
                            error = response.message()
                        )
                    )
                }
            } catch (e: Exception) {
                println(e.toString())
                uiStateFlow.emit(
                    uiStateFlow.value.copy(
                        isLoading = false,
                        error = e.message.toString()
                    )
                )
            }
        }
    }
}