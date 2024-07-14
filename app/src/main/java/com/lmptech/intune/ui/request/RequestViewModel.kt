package com.lmptech.intune.ui.request

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmptech.intune.data.local.database.repository.ChatRepository
import com.lmptech.intune.data.local.database.repository.UserRepository
import com.lmptech.intune.model.RawRequestModel
import com.lmptech.intune.model.UserModel
import com.lmptech.intune.data.remote.repository.RemoteChatRepository
import com.lmptech.intune.data.remote.repository.RemoteUserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface RequestViewState {
    val loadingRawRequest:Boolean
    val loadingRequest:Boolean
    val error:String

    data class NothingIsLoaded(
        override val loadingRawRequest: Boolean,
        override val loadingRequest: Boolean,
        override val error: String
    ):RequestViewState

    data class RawRequestLoaded(
        override val loadingRawRequest: Boolean,
        override val loadingRequest: Boolean,
        override val error: String,
        val rawRequests: List<RawRequestModel>
    ):RequestViewState

    data class RequestLoaded(
        override val loadingRawRequest: Boolean,
        override val loadingRequest: Boolean,
        override val error: String,
        val requests: List<UserModel>
    ):RequestViewState
}

private data class RequestViewModelState(
    val loadingRawRequest:Boolean = false,
    val loadingRequest:Boolean = false,
    val error:String = "",
    val rawRequests: List<RawRequestModel> = emptyList(),
    val requests: List<UserModel> = emptyList()
) {
    fun toViewState(): RequestViewState = if (rawRequests.isNotEmpty()) {
        RequestViewState.RawRequestLoaded(loadingRawRequest, loadingRequest, error, rawRequests)
    } else if (requests.isNotEmpty()) {
        RequestViewState.RequestLoaded(loadingRawRequest, loadingRequest, error, requests)
    } else {
        RequestViewState.NothingIsLoaded(loadingRawRequest, loadingRequest, error)
    }
}

class RequestViewModel(
    private val chatRepository: ChatRepository,
    private val remoteChatRepository: RemoteChatRepository,
    private val userRepository: UserRepository,
    private val userRemoteUserRepository: RemoteUserRepository
) : ViewModel() {
    private val privateState:MutableStateFlow<RequestViewModelState> = MutableStateFlow(RequestViewModelState(loadingRawRequest = true, loadingRequest = true))

    val requestViewState: StateFlow<RequestViewState> = privateState.map {
        it.toViewState()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        privateState.value.toViewState()
    )


    fun fetchFromServer() {
        viewModelScope.launch {
            val userRequests = remoteChatRepository.getUserRequests()
            if (userRequests.isSuccessful) {
                privateState.emit(privateState.value.copy(loadingRawRequest = false, rawRequests = userRequests.body() ?: emptyList()))
                // save this in request db

            }
        }
    }

}