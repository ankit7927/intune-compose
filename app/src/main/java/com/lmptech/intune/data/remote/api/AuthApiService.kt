package com.lmptech.intune.data.remote.api

import com.lmptech.intune.data.model.LoginRequestModel
import com.lmptech.intune.data.model.LoginResponseModel
import com.lmptech.intune.data.model.RegisterRequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequestModel
    ): Response<LoginResponseModel>

    @POST("/auth/register")
    suspend fun register(registerRequestModel: RegisterRequestModel): Response<String>

}