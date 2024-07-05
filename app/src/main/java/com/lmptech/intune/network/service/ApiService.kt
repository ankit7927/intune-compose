package com.lmptech.intune.network.service

import com.lmptech.intune.network.NetworkConfig
import retrofit2.Retrofit

object ApiService {

    val authApiService: AuthApiService by lazy {
        NetworkConfig.getInstance().create(AuthApiService::class.java)
    }

}