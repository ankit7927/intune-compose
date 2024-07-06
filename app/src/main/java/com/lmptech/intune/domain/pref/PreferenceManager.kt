package com.lmptech.intune.domain.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


object PreferenceManager {
    private const val PREFERENCES_NAME = "app_preferences"
    private const val TOKEN_KEY = "token_key"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun saveToken(context: Context, token: String) {
        getSharedPreferences(context).edit {
            putString(TOKEN_KEY, token)
        }
    }

    fun getToken(context: Context): String? {
        return getSharedPreferences(context).getString(TOKEN_KEY, "")
    }

    fun isTokenExists(context: Context): Boolean {
        return getSharedPreferences(context).contains(TOKEN_KEY)
    }

    fun clearToken(context: Context) {
        getSharedPreferences(context).edit {
            remove(TOKEN_KEY)
        }
    }
}
