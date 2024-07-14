package com.lmptech.intune.data.remote.api

import com.lmptech.intune.model.ChatModel
import com.lmptech.intune.model.RawRequestModel
import com.lmptech.intune.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatApiService {

    @GET("/chat/user")
    suspend fun getAllChats():Response<List<ChatModel>>

    @GET("/chat/user-request")
    suspend fun getUserRequests():Response<List<RawRequestModel>>


}