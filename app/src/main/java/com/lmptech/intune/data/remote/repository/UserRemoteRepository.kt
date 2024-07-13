package com.lmptech.intune.data.remote.repository

import com.lmptech.intune.data.model.UserModel
import com.lmptech.intune.data.remote.api.UserApiService
import retrofit2.Response

interface RemoteUserRepository {
    suspend fun getUserData(): Response<UserModel>
    suspend fun updateUser(user: UserModel): Response<UserModel>
}

class RemoteUserRepositoryImpl(private val userApiService: UserApiService) : RemoteUserRepository {
    override suspend fun getUserData(): Response<UserModel> = userApiService.getUserData()

    override suspend fun updateUser(user: UserModel): Response<UserModel> = userApiService.updateUser(user)
}
