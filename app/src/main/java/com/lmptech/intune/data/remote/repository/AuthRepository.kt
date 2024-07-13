package com.lmptech.intune.data.remote.repository

import com.lmptech.intune.data.remote.api.AuthApiService
import com.lmptech.intune.data.model.LoginRequestModel
import com.lmptech.intune.data.model.LoginResponseModel
import com.lmptech.intune.data.model.RegisterRequestModel
import retrofit2.Response

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequestModel): Response<LoginResponseModel>
    suspend fun register(registerRequestModel: RegisterRequestModel) :Response<String>

}

class AuthRepositoryImpl(private val apiService: AuthApiService) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequestModel): Response<LoginResponseModel> =
        apiService.login(loginRequest)

    override suspend fun register(registerRequestModel: RegisterRequestModel): Response<String> =
        apiService.register(registerRequestModel)
}