package com.lmptech.intune.network.service

import android.content.Context
import com.lmptech.intune.network.NetworkConfig
import retrofit2.Retrofit

class ApiService(private val context: Context) {

    val authApiService: AuthApiService by lazy {
        NetworkConfig.getInstance(context).create(AuthApiService::class.java)
    }

    val chatApiService: ChatApiService by lazy {
        NetworkConfig.getInstance(context).create(ChatApiService::class.java)
    }

    val userApiService: UserApiService by lazy {
        NetworkConfig.getInstance(context).create(UserApiService::class.java)
    }

}