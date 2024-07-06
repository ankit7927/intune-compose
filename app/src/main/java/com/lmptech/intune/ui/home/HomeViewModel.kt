package com.lmptech.intune.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmptech.intune.data.model.ChatModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface HomeState {
    val loading: Boolean
    val error: String
    val activeChatId: String

    data class HomeWithChat(
        override val loading: Boolean,
        override val error: String,
        override val activeChatId: String,
        val chat: ChatModel,
    ) : HomeState

    data class HomeWithoutChat(
        override val loading: Boolean,
        override val error: String,
        override val activeChatId: String
    ): HomeState
}

private data class HomeViewModelState(
    val loading: Boolean = false,
    val error: String = "",
    val activeChatId: String = "",
    val chat:ChatModel? = null
) {
    fun toHomeState() : HomeState = if (chat != null) {
        HomeState.HomeWithChat(loading, error, activeChatId, chat)
    } else  {
        HomeState.HomeWithoutChat(loading, error, activeChatId)
    }
}

class HomeViewModel() : ViewModel() {
    private val homeUiState: MutableStateFlow<HomeViewModelState> = MutableStateFlow(
        HomeViewModelState()
    )

    val homeState: StateFlow<HomeState> = homeUiState.map {
        it.toHomeState()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, HomeViewModelState().toHomeState())

    fun loadChat(chatId:String) {
        viewModelScope.launch {
            homeUiState.emit(homeUiState.value.copy(loading = true))

        }
    }
}