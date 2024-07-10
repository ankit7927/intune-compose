package com.lmptech.intune.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmptech.intune.data.model.UserModel
import com.lmptech.intune.data.model.response.ChatResponseModel
import com.lmptech.intune.domain.repository.UserCacheRepo
import com.lmptech.intune.network.repository.ChatRepository
import com.lmptech.intune.network.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class DrawerState(
    val loading:Boolean = false,
    val loadingUser:Boolean = false,
    val error:String = "",
    val chats: List<ChatResponseModel> = emptyList(),
    val user: UserModel? = null

)

class DrawerViewModel(
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository,
    private val userCacheRepo: UserCacheRepo
) : ViewModel() {
    private val drawerState: MutableStateFlow<DrawerState> = MutableStateFlow(DrawerState().copy(loading = true, loadingUser = true))

    val drawerUiState: StateFlow<DrawerState>
        get() = drawerState

    init {
        getChats()

        viewModelScope.launch {
            drawerState.emit(drawerUiState.value.copy(loadingUser = true))

            val user = userCacheRepo.getUser()
            if (user.count() == 0) {
                getUserData()
            } else {
                drawerState.emit(drawerUiState.value.copy(loadingUser = false, user = user.first()))
            }
        }
    }

    private fun getChats() {
        viewModelScope.launch {
            drawerState.emit(
                drawerUiState.value.copy(loading = true)
            )

            try {
                val chatRes = chatRepository.getAllChats()

                if (chatRes.isSuccessful && chatRes.body() != null) {
                    drawerState.emit(
                        drawerUiState.value.copy(loading = false, chats = chatRes.body()!!)
                    )
                } else {
                    drawerState.emit(
                        drawerUiState.value.copy(loading = false, error = chatRes.message())
                    )
                }
            } catch (e: Exception) {
                drawerState.emit(
                    drawerUiState.value.copy(loading = false, error = e.message.toString())
                )
            }

        }
    }

    private suspend fun getUserData() {
        println("going to network")
        try {
            val userRes = userRepository.getUserData()

            if (userRes.isSuccessful && userRes.body() != null) {
                userCacheRepo.insertUser(userRes.body()!!)
                drawerState.emit(drawerState.value.copy(loadingUser = false, user = userRes.body()!!))
            } else {
                drawerState.emit(
                    drawerState.value.copy(loadingUser = false, error = userRes.message()))
            }
        } catch (e:Exception) {
            drawerState.emit(
                drawerState.value.copy(loadingUser = false, error = e.message.toString())
            )
        }
    }
}