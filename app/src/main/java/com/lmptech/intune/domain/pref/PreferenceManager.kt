package com.lmptech.intune.domain.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.lmptech.intune.data.model.response.LoginResponseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
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

    fun getAccessToken():Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { it[ACCESS_TOKEN_KEY]?: "" }
    }

    fun getRefreshToken():Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { it[REFRESH_TOKEN_KEY]?: "" }
    }

    fun isUserLoggedIn():Flow<Boolean> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { it[ACCESS_TOKEN_KEY] != null }
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

