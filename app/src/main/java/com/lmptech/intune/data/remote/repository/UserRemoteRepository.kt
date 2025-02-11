package com.lmptech.intune.data.remote.repository

import com.lmptech.intune.model.UserModel
import com.lmptech.intune.data.remote.api.UserApiService
import retrofit2.Response

interface RemoteUserRepository {
    suspend fun getUserData(): Response<UserModel>
    suspend fun updateUser(user: UserModel, section:String): Response<UserModel>
    suspend fun getUserProfiles(userIds: List<String>): Response<List<UserModel>>
}

class RemoteUserRepositoryImpl(private val userApiService: UserApiService) : RemoteUserRepository {
    override suspend fun getUserData(): Response<UserModel> = userApiService.getUserData()

    override suspend fun updateUser(user: UserModel, section: String): Response<UserModel> =
        userApiService.updateUser(user, section)

    override suspend fun getUserProfiles(userIds: List<String>): Response<List<UserModel>> =
        userApiService.getUserProfiles(userIds)
}
