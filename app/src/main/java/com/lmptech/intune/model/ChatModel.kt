package com.lmptech.intune.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "chats")
data class ChatModel(
    @PrimaryKey
    val id: String,
    val roomId: String,
    val name: String,
)

data class RawRequestModel(
    val id: String,
    val senderId: String,
    val receiverId: String,
)
