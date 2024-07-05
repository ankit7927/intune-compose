package com.lmptech.intune.data

import android.content.Context
import com.lmptech.intune.data.repository.AuthRepository
import com.lmptech.intune.data.repository.AuthRepositoryImpl
import com.lmptech.intune.network.service.ApiService
import com.lmptech.intune.network.service.AuthApiService

interface AppContainer {
    val authRepository:AuthRepository
}

class DefaultAppContainer (private val context: Context) : AppContainer {

    private val authApiService : AuthApiService = ApiService.authApiService

    override val authRepository: AuthRepository
        get() = AuthRepositoryImpl(authApiService)
}