package com.lmptech.intune.data

import android.content.Context
import com.lmptech.intune.data.local.database.AppDatabase
import com.lmptech.intune.data.local.database.repository.ChatRepository
import com.lmptech.intune.data.local.database.repository.ChatRepositoryImpl
import com.lmptech.intune.data.local.database.repository.UserRepository
import com.lmptech.intune.data.local.database.repository.UserRepositoryImpl
import com.lmptech.intune.data.remote.repository.AuthRepository
import com.lmptech.intune.data.remote.repository.AuthRepositoryImpl

import com.lmptech.intune.data.remote.api.ApiService
import com.lmptech.intune.data.remote.repository.RemoteChatRepository
import com.lmptech.intune.data.remote.repository.RemoteChatRepositoryImpl
import com.lmptech.intune.data.remote.repository.RemoteUserRepository
import com.lmptech.intune.data.remote.repository.RemoteUserRepositoryImpl

interface AppContainer {
    val authRepository: AuthRepository
    val remoteUserRepository: RemoteUserRepository
    val remoteChatRepository: RemoteChatRepository

    val chatRepository: ChatRepository
    val userRepository:UserRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    override val authRepository: AuthRepository
        get() = AuthRepositoryImpl(ApiService(context).authApiService)

    override val remoteUserRepository: RemoteUserRepository
        get() = RemoteUserRepositoryImpl(ApiService(context).userApiService)

    override val remoteChatRepository: RemoteChatRepository
        get() = RemoteChatRepositoryImpl(ApiService(context).chatApiService)

    override val chatRepository: ChatRepository
        get() = ChatRepositoryImpl(AppDatabase.getInstance(context).chatDao())

    override val userRepository: UserRepository
        get() = UserRepositoryImpl(AppDatabase.getInstance(context).userDao())
}