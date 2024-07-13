package com.lmptech.intune.data.local.database.repository

import com.lmptech.intune.data.local.database.dao.ChatDao
import com.lmptech.intune.data.model.ChatModel
import com.lmptech.intune.data.remote.api.ChatApiService
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun getAllChats():List<ChatModel>

    suspend fun insertChats(chats:List<ChatModel>)

    suspend fun deleteAllChats()
}

class ChatRepositoryImpl(private val chatDao: ChatDao) : ChatRepository {
    override suspend fun getAllChats(): List<ChatModel> = chatDao.getAllChats()

    override suspend fun insertChats(chats: List<ChatModel>) = chatDao.insertChats(chats)

    override suspend fun deleteAllChats() = chatDao.deleteAllChats()
}