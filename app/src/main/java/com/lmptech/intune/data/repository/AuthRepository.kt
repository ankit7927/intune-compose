package com.lmptech.intune.data.repository

import com.lmptech.intune.data.model.LoginResponseModel
import com.lmptech.intune.network.service.AuthApiService
import retrofit2.Response

interface AuthRepository {
    suspend fun login(email: String, password: String): Response<LoginResponseModel>
    suspend fun register(name: String, username: String, email: String, password: String) :Response<String>

}

class AuthRepositoryImpl(private val apiService: AuthApiService) : AuthRepository {
    override suspend fun login(email: String, password: String): Response<LoginResponseModel> =
        apiService.login(email, password)

    override suspend fun register(
        name: String,
        username: String,
        email: String,
        password: String
    ): Response<String> = apiService.register(name, username, email, password)

}