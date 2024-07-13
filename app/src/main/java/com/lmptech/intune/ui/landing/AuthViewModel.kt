package com.lmptech.intune.ui.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmptech.intune.data.model.LoginRequestModel
import com.lmptech.intune.data.model.LoginResponseModel
import com.lmptech.intune.data.model.RegisterRequestModel
import com.lmptech.intune.data.remote.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AuthUiState (
    val name:String = "",
    val username:String = "",
    val email:String = "",
    val password:String = "",
    val isLoading:Boolean = false,
    val error:String = "",
    var loginScreen: Boolean = true,
    var token: LoginResponseModel? = null
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
                    username = "",
                    error = ""
                )
            )
        }
    }

    fun savingToken() {
        viewModelScope.launch {
            uiStateFlow.emit(uiStateFlow.value.copy(isLoading = true))
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
            uiStateFlow.emit(uiState.value.copy(isLoading = true, error = ""))
            try {

                if (uiState.value.loginScreen) {
                    val response = authRepository.login(LoginRequestModel(uiState.value.email, uiState.value.password))
                    if (response.isSuccessful) {
                        uiStateFlow.emit(uiState.value.copy(isLoading = false, token = response.body()))
                    } else {
                        uiStateFlow.emit(
                            uiStateFlow.value.copy(
                                isLoading = false,
                                error = "Bad credentials"
                            )
                        )
                    }

                } else {
                    val response = authRepository.register(
                        RegisterRequestModel(
                            email = uiState.value.email,
                            password = uiState.value.password,
                            username = uiState.value.username,
                            name = uiState.value.name
                        )
                    )

                    if (response.isSuccessful) {
                        uiStateFlow.emit(uiState.value.copy(isLoading = false, loginScreen = true, ))
                    } else {
                        uiStateFlow.emit(
                            uiStateFlow.value.copy(
                                isLoading = false,
                                error = "something went wrong"
                            )
                        )
                    }
                }


            } catch (e: Exception) {
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