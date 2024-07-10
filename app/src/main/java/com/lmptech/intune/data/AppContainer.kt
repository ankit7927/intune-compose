package com.lmptech.intune.data

import android.content.Context
import com.lmptech.intune.domain.UserDatabase
import com.lmptech.intune.domain.repository.UserCacheRepo
import com.lmptech.intune.domain.repository.UserCacheRepoImpl
import com.lmptech.intune.network.repository.AuthRepository
import com.lmptech.intune.network.repository.AuthRepositoryImpl
import com.lmptech.intune.network.repository.ChatRepository
import com.lmptech.intune.network.repository.ChatRepositoryImpl
import com.lmptech.intune.network.repository.UserRepository
import com.lmptech.intune.network.repository.UserRepositoryImpl
import com.lmptech.intune.network.service.ApiService
import com.lmptech.intune.network.service.UserApiService

interface AppContainer {
    val authRepository: AuthRepository
    val chatRepository: ChatRepository
    val userRepository: UserRepository

    val userCacheRepo: UserCacheRepo
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    override val authRepository: AuthRepository
        get() = AuthRepositoryImpl(ApiService(context).authApiService)

    override val chatRepository: ChatRepository
        get() = ChatRepositoryImpl(ApiService(context).chatApiService)

    override val userRepository: UserRepository
        get() = UserRepositoryImpl(ApiService(context).userApiService)

    override val userCacheRepo: UserCacheRepo
        get() = UserCacheRepoImpl(UserDatabase.getInstance(context).userDao())
}