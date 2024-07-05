package com.lmptech.intune.network.service

import com.lmptech.intune.data.model.LoginResponseModel
import retrofit2.Response
import retrofit2.http.POST

interface AuthApiService {
    @POST("/auth/login")
    suspend fun login(email:String, password:String): Response<LoginResponseModel>

    @POST("/auth/register")
    suspend fun register(name:String, username:String, email:String, password:String): Response<String>

}