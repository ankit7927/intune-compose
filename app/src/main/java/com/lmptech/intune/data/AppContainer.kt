package com.lmptech.intune.data

import android.content.Context
import com.lmptech.intune.data.repository.AuthRepository
import com.lmptech.intune.data.repository.AuthRepositoryImpl
import com.lmptech.intune.data.repository.ChatRepository
import com.lmptech.intune.data.repository.ChatRepositoryImpl
import com.lmptech.intune.network.service.ApiService

interface AppContainer {
    val authRepository:AuthRepository
    val chatRepository:ChatRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    override val authRepository: AuthRepository
        get() = AuthRepositoryImpl(ApiService.authApiService)

    override val chatRepository: ChatRepository
        get() = ChatRepositoryImpl(ApiService.chatApiService)
}