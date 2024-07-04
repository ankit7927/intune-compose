package com.lmptech.intune.data.repository

interface AuthRepository {
    suspend fun login(email: String, password: String)
    suspend fun register(name: String, username: String, email: String, password: String)

}