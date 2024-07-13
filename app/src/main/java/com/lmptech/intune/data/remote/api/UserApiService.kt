package com.lmptech.intune.data.remote.api

import com.lmptech.intune.data.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface UserApiService {

    @GET("/user/me")
    suspend fun getUserData(): Response<UserModel>

    @PUT("/user/update")
    suspend fun updateUser(
        @Body user: UserModel
    ): Response<UserModel>
}