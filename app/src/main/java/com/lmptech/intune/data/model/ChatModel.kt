package com.lmptech.intune.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "chats")
data class ChatModel(
    @PrimaryKey
    val id: String,
    val roomId: String,
    val name: String,
)

data class ChatRequestModel(
    val id: String,
    val senderId: String,
    val receiverId: String,
)
