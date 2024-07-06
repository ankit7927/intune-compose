package com.lmptech.intune.data.model.response

data class ChatResponseModel(
    val id: String,
    val members: List<String>,
    val messages: List<Any>,
    val name: String,
    val roomId: String
)