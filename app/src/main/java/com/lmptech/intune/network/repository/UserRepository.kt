package com.lmptech.intune.network.repository

import com.lmptech.intune.data.model.UserModel
import com.lmptech.intune.network.service.UserApiService
import retrofit2.Response

interface UserRepository {
    suspend fun getUserData(): Response<UserModel>
}

class UserRepositoryImpl(private val userApiService: UserApiService) : UserRepository {
    override suspend fun getUserData(): Response<UserModel> = userApiService.getUserData()
}
