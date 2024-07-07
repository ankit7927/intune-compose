package com.lmptech.intune.network.service

import com.lmptech.intune.data.model.LoginRequest
import com.lmptech.intune.data.model.response.LoginResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponseModel>

    @POST("/auth/register")
    suspend fun register(name:String, username:String, email:String, password:String): Response<String>

}