package com.lmptech.intune.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmptech.intune.data.local.database.repository.ChatRepository
import com.lmptech.intune.data.local.database.repository.UserRepository
import com.lmptech.intune.data.model.ChatModel
import com.lmptech.intune.data.model.UserModel
import com.lmptech.intune.data.remote.repository.RemoteChatRepository
import com.lmptech.intune.data.remote.repository.RemoteUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class DrawerState(
    val loading:Boolean = false,
    val loadingUser:Boolean = false,
    val error:String = "",
    val chats: List<ChatModel> = emptyList(),
    val user: UserModel? = null

)

class DrawerViewModel(
    private val remoteChatRepository: RemoteChatRepository,
    private val chatRepository: ChatRepository,
    private val remoteUserRepository: RemoteUserRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val drawerState: MutableStateFlow<DrawerState> = MutableStateFlow(DrawerState().copy(loading = true, loadingUser = true))

    val drawerUiState: StateFlow<DrawerState>
        get() = drawerState

    init {

        viewModelScope.launch (Dispatchers.IO) {
            getChats()

            drawerState.emit(drawerUiState.value.copy(loadingUser = true))

            val user = userRepository.getUserData()
            if (user == null) {
                getUserData()
            } else {
                drawerState.emit(drawerUiState.value.copy(loadingUser = false, user = user))
            }
        }
    }

    private suspend fun getChats() {
        drawerState.emit(drawerUiState.value.copy(loading = true))

        try {
            val chatRes = chatRepository.getAllChats()

            if (chatRes.isEmpty()) {
                val allChats = remoteChatRepository.getAllChats()

                if (allChats.isSuccessful && allChats.body() != null) {
                    chatRepository.insertChats(allChats.body()!!)
                    drawerState.emit(
                        drawerUiState.value.copy(loading = false, chats = allChats.body()!!)
                    )
                } else {
                    drawerState.emit(
                        drawerUiState.value.copy(loading = false, error = allChats.message())
                    )
                }
            } else {
                drawerState.emit(
                    drawerUiState.value.copy(loading = false, chats = chatRes)
                )
            }
        } catch (e: Exception) {
            drawerState.emit(
                drawerUiState.value.copy(loading = false, error = e.message.toString())
            )
        }
    }

    private suspend fun getUserData() {
        println("going to network")
        try {
            val userRes = remoteUserRepository.getUserData()

            if (userRes.isSuccessful && userRes.body() != null) {
                userRepository.insertUser(userRes.body()!!)
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