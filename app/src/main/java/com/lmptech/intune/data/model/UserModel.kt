package com.lmptech.intune.data.model

import androidx.room.Entity

@Entity(tableName = "users")
data class UserModel(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val password: String,
    val verified: Boolean,
)
