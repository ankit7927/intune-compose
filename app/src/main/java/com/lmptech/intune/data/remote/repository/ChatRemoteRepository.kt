package com.lmptech.intune.data.remote.repository

import com.lmptech.intune.data.model.ChatModel
import com.lmptech.intune.data.remote.api.ChatApiService
import retrofit2.Response

interface RemoteChatRepository {
    suspend fun getAllChats():Response<List<ChatModel>>
}

class RemoteChatRepositoryImpl (private val chatApiService: ChatApiService) : RemoteChatRepository {
    override suspend fun getAllChats(): Response<List<ChatModel>>
        = chatApiService.getAllChats()

}