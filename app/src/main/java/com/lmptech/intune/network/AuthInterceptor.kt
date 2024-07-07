package com.lmptech.intune.network

import android.content.Context
import com.lmptech.intune.data.pref.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {

    private val token = PreferenceManager.getAccessToken(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url.toString()

        if (requestUrl.contains("auth/login") || requestUrl.contains("auth/register")) {
            return chain.proceed(request)
        }

        println("applying token")
        val authRequest = request.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(authRequest)
    }
}