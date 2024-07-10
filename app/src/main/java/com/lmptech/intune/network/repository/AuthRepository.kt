package com.lmptech.intune.network.repository

import com.lmptech.intune.data.model.LoginRequest
import com.lmptech.intune.data.model.response.LoginResponseModel
import com.lmptech.intune.network.service.AuthApiService
import retrofit2.Response

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): Response<LoginResponseModel>
    suspend fun register(name: String, username: String, email: String, password: String) :Response<String>

}

class AuthRepositoryImpl(private val apiService: AuthApiService) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponseModel> =
        apiService.login(loginRequest)

    override suspend fun register(
        name: String,
        username: String,
        email: String,
        password: String
    ): Response<String> = apiService.register(name, username, email, password)

}