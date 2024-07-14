package com.lmptech.intune.data.remote.repository

import com.lmptech.intune.model.ChatModel
import com.lmptech.intune.model.RawRequestModel
import com.lmptech.intune.data.remote.api.ChatApiService
import retrofit2.Response

interface RemoteChatRepository {
    suspend fun getAllChats():Response<List<ChatModel>>

    suspend fun getUserRequests(): Response<List<RawRequestModel>>
}

class RemoteChatRepositoryImpl (private val chatApiService: ChatApiService) : RemoteChatRepository {
    override suspend fun getAllChats(): Response<List<ChatModel>>
        = chatApiService.getAllChats()

    override suspend fun getUserRequests(): Response<List<RawRequestModel>>
        = chatApiService.getUserRequests()

}