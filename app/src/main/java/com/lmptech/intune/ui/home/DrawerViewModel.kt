package com.lmptech.intune.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmptech.intune.data.model.response.ChatResponseModel
import com.lmptech.intune.data.repository.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class DrawerState(
    val loading:Boolean = false,
    val error:String = "",
    val chats: List<ChatResponseModel> = emptyList()
)

class DrawerViewModel (private val chatRepository: ChatRepository) : ViewModel() {
    private val drawerState: MutableStateFlow<DrawerState> = MutableStateFlow(DrawerState())

    val drawerUiState: StateFlow<DrawerState>
        get() = drawerState

    init {
        println("launching")
        getChats()
    }

    private fun getChats() {
        viewModelScope.launch {
            drawerState.emit(
                drawerUiState.value.copy(loading = true)
            )

            try {
                val chatRes = chatRepository.getAllChats()

                println(chatRes.body().toString())

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
}