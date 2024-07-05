package com.lmptech.intune.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConfig private constructor() {
    companion object {
        private const val BASE_URL = "http://localhost:8080"

        @Volatile
        private var instance: Retrofit? = null

        private val client = OkHttpClient.Builder()
//            .addInterceptor(ApiKeyInterceptor())
            .build()

        private fun createRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        fun getInstance(): Retrofit {
            return instance ?: synchronized(this) {
                instance ?: createRetrofit().also { instance = it }
            }
        }
    }

}