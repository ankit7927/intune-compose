package com.lmptech.intune.data.remote.api

import android.content.Context
import com.lmptech.intune.data.remote.RemoteConfig


class ApiService(private val context: Context) {

    val authApiService: AuthApiService by lazy {
        RemoteConfig.getInstance(context).create(AuthApiService::class.java)
    }

    val chatApiService: ChatApiService by lazy {
        RemoteConfig.getInstance(context).create(ChatApiService::class.java)
    }

    val userApiService: UserApiService by lazy {
        RemoteConfig.getInstance(context).create(UserApiService::class.java)
    }

}