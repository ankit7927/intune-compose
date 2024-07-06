package com.lmptech.intune.network.service

import com.lmptech.intune.data.model.response.ChatResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ChatApiService {

    @GET("/chat/user")
    suspend fun getAllChats():Response<List<ChatResponseModel>>
}