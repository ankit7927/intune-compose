package com.lmptech.intune.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lmptech.intune.data.model.ChatModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Query("SELECT * FROM chats")
    fun getAllChats(): List<ChatModel>

    @Query("SELECT * FROM chats WHERE id = :id")
    fun getChatById(id: String): ChatModel

    @Query("SELECT * FROM chats WHERE roomId = :roomId")
    fun getChatsByRoomId(roomId: String): List<ChatModel>

    @Insert
    suspend fun insertChat(chat: ChatModel)

    @Insert
    suspend fun insertChats(chats: List<ChatModel>)

    @Query("DELETE FROM chats")
    suspend fun deleteAllChats()

    @Query("DELETE FROM chats WHERE id = :id")
    suspend fun deleteChatById(id: String)

}