package com.lmptech.intune.domain.repository

import com.lmptech.intune.data.model.UserModel
import com.lmptech.intune.domain.dao.UserCacheDao
import kotlinx.coroutines.flow.Flow

interface UserCacheRepo {
    fun getUser(): Flow<UserModel>
    fun isUserCached(): Flow<Boolean>
    suspend fun insertUser(user: UserModel)
    suspend fun updateUser(user: UserModel)
    suspend fun deleteUser(user: UserModel)
}

class UserCacheRepoImpl (private val userCacheDao: UserCacheDao) : UserCacheRepo {
    override fun getUser(): Flow<UserModel> = userCacheDao.getUser()

    override fun isUserCached(): Flow<Boolean> = userCacheDao.isUserCached()

    override suspend fun insertUser(user: UserModel) = userCacheDao.insertUser(user)

    override suspend fun updateUser(user: UserModel) = userCacheDao.updateUser(user)

    override suspend fun deleteUser(user: UserModel) = userCacheDao.deleteUser(user)

}