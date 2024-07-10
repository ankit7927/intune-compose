package com.lmptech.intune.data

import android.content.Context
import com.lmptech.intune.domain.UserDatabase
import com.lmptech.intune.domain.repository.UserCacheRepo
import com.lmptech.intune.domain.repository.UserCacheRepoImpl
import com.lmptech.intune.network.repository.AuthRepository
import com.lmptech.intune.network.repository.AuthRepositoryImpl
import com.lmptech.intune.network.repository.ChatRepository
import com.lmptech.intune.network.repository.ChatRepositoryImpl
import com.lmptech.intune.network.service.ApiService

interface AppContainer {
    val authRepository: AuthRepository
    val chatRepository: ChatRepository
    val userCacheRepo: UserCacheRepo
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    override val authRepository: AuthRepository
        get() = AuthRepositoryImpl(ApiService(context).authApiService)

    override val chatRepository: ChatRepository
        get() = ChatRepositoryImpl(ApiService(context).chatApiService)

    override val userCacheRepo: UserCacheRepo
        get() = UserCacheRepoImpl(UserDatabase.getInstance(context).userDao())
}