package com.lmptech.intune.data.remote.api

import com.lmptech.intune.model.LoginRequestModel
import com.lmptech.intune.model.LoginResponseModel
import com.lmptech.intune.model.RegisterRequestModel
import com.lmptech.intune.model.RegisterResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequestModel
    ): Response<LoginResponseModel>

    @POST("/user/register")
    suspend fun register(@Body registerRequestModel: RegisterRequestModel): Response<RegisterResponseModel>

}