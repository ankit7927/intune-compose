package com.lmptech.intune.data.remote

import android.content.Context
import com.lmptech.intune.util.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteConfig private constructor() {
    companion object {
        private const val BASE_URL = "http://192.168.212.24:8080"

        @Volatile
        private var instance: Retrofit? = null

        private fun createRetrofit(context: Context): Retrofit {

            val client = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(context))
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        fun getInstance(context: Context): Retrofit {
            return instance ?: synchronized(this) {
                instance ?: createRetrofit(context).also { instance = it }
            }
        }
    }
}