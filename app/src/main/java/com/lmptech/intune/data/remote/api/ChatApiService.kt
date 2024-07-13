package com.lmptech.intune.data.remote.api

import com.lmptech.intune.data.model.ChatModel
import retrofit2.Response
import retrofit2.http.GET

interface ChatApiService {

    @GET("/chat/user")
    suspend fun getAllChats():Response<List<ChatModel>>
}