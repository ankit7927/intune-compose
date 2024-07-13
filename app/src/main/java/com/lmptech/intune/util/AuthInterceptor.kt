package com.lmptech.intune.util

import android.content.Context
import com.lmptech.intune.data.local.pref.DataStoreManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val dataStoreManager = DataStoreManager.getInstance(context)

    private val token = dataStoreManager.accessToken.stateIn(scope, SharingStarted.Eagerly, "")

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url.toString()

        if (requestUrl.contains("auth/login") || requestUrl.contains("auth/register")) {
            return chain.proceed(request)
        }

        val authRequest = request.newBuilder()
            .header("Authorization", "Bearer ${token.value}")
            .build()

        return chain.proceed(authRequest)
    }
}