package com.lmptech.intune.data.local.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.lmptech.intune.data.model.LoginResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager private constructor(context: Context) {
    private val dataStore = context.dataStore

    companion object {
        @Volatile
        private var INSTANCE: DataStoreManager? = null

        fun getInstance(context: Context): DataStoreManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: DataStoreManager(context).also { INSTANCE = it }
            }
        }

        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }

    val accessToken: Flow<String> = dataStore.data.map {
        it[ACCESS_TOKEN_KEY] ?: ""
    }

    val refreshToken: Flow<String> = dataStore.data.map {
        it[REFRESH_TOKEN_KEY] ?: ""
    }

    val isLoggedIn: Flow<Boolean> = dataStore.data.map {
        it[ACCESS_TOKEN_KEY] != null
    }


    suspend fun saveToken(loginResponseModel: LoginResponseModel) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = loginResponseModel.accessToken
            preferences[REFRESH_TOKEN_KEY] = loginResponseModel.refreshToken
        }
    }

    suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_TOKEN_KEY)
            preferences.remove(REFRESH_TOKEN_KEY)
        }
    }

}

