package com.lmptech.intune.data.model

data class UserModel(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val password: String,
    val verified: Boolean,

)
