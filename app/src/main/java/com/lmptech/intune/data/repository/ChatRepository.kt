package com.lmptech.intune.data.repository

import com.lmptech.intune.data.model.response.ChatResponseModel
import com.lmptech.intune.network.service.ChatApiService
import retrofit2.Response

interface ChatRepository {
    suspend fun getAllChats():Response<List<ChatResponseModel>>
}

class ChatRepositoryImpl (private val chatApiService: ChatApiService) : ChatRepository {
    override suspend fun getAllChats(): Response<List<ChatResponseModel>>
        = chatApiService.getAllChats()

}