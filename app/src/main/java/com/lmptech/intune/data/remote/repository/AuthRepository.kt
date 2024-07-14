package com.lmptech.intune.data.remote.repository

import com.lmptech.intune.data.remote.api.AuthApiService
import com.lmptech.intune.model.LoginRequestModel
import com.lmptech.intune.model.LoginResponseModel
import com.lmptech.intune.model.RegisterRequestModel
import com.lmptech.intune.model.RegisterResponseModel
import retrofit2.Response

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequestModel): Response<LoginResponseModel>
    suspend fun register(registerRequestModel: RegisterRequestModel) :Response<RegisterResponseModel>

}

class AuthRepositoryImpl(private val apiService: AuthApiService) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequestModel): Response<LoginResponseModel> =
        apiService.login(loginRequest)

    override suspend fun register(registerRequestModel: RegisterRequestModel): Response<RegisterResponseModel> =
        apiService.register(registerRequestModel)
}