package com.lmptech.intune.data.remote.api

import com.lmptech.intune.data.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApiService {

    @GET("/user/me")
    suspend fun getUserData(): Response<UserModel>

    @PUT("/user/update/{section}")
    suspend fun updateUser(
        @Body user: UserModel,
        @Path("section") section: String
    ): Response<UserModel>
}