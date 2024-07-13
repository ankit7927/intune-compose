package com.lmptech.intune.data.remote.repository

import com.lmptech.intune.data.model.UserModel
import com.lmptech.intune.data.remote.api.UserApiService
import retrofit2.Response

interface RemoteUserRepository {
    suspend fun getUserData(): Response<UserModel>
}

class RemoteUserRepositoryImpl(private val userApiService: UserApiService) : RemoteUserRepository {
    override suspend fun getUserData(): Response<UserModel> = userApiService.getUserData()
}
