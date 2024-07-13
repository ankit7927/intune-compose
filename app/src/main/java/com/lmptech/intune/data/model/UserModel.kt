package com.lmptech.intune.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserModel(
    @PrimaryKey
    val id: String,
    var name: String,
    val username: String,
    val email: String,
)
