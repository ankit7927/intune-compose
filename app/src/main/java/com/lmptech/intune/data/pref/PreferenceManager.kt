package com.lmptech.intune.data.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.lmptech.intune.data.model.response.LoginResponseModel
import kotlinx.coroutines.flow.first


object PreferenceManager {
    private const val PREFERENCES_NAME = "app_preferences"
    private const val ACCESS_TOKEN_KEY = "access_token"
    private const val REFRESH_TOKEN_KEY = "refresh_token"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, responseModel: LoginResponseModel) {
        getSharedPreferences(context).edit {
            putString(ACCESS_TOKEN_KEY, responseModel.accessToken)
            putString(REFRESH_TOKEN_KEY, responseModel.refreshToken)
        }
    }

    fun getAccessToken(context: Context): String? {
        val string = getSharedPreferences(context).getString(ACCESS_TOKEN_KEY, null)
        println(string)
        return string
    }

    fun getRefreshToken(context: Context): String? {
        return getSharedPreferences(context).getString(REFRESH_TOKEN_KEY, "")
    }

    fun isTokenExists(context: Context): Boolean {
        return getSharedPreferences(context).contains(ACCESS_TOKEN_KEY) &&
                getSharedPreferences(context).contains(REFRESH_TOKEN_KEY)
    }

    fun clearToken(context: Context) {
        getSharedPreferences(context).edit {
            remove(ACCESS_TOKEN_KEY)
            remove(REFRESH_TOKEN_KEY)
        }
    }
}
