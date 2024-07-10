package com.lmptech.intune.network.service

import com.lmptech.intune.data.model.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface UserApiService {

    @GET("/user/me")
    suspend fun getUserData(): Response<UserModel>
}