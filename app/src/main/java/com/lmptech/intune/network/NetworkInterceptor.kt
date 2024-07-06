package com.lmptech.intune.network

import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {

    private val token = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MjAyNDk0OTIsImV4cCI6MTcyMDI1MzA5Miwic3ViIjoiNjY4NzdlNmU4ZGFlZGMwZjdkMjZhY2MzIn0.wzxT7EC_w2d3jjbfES3Ph3yNgo8qTVilbTfJUWTCuPo"

    override fun intercept(chain: Interceptor.Chain): Response {
        val authRequest = chain.request().newBuilder()
            .header("Authorization", "Bearer $token").build()

        return chain.proceed(authRequest)
    }
}