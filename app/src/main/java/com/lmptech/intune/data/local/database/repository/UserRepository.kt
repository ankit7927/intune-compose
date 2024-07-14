package com.lmptech.intune.data.local.database.repository

import com.lmptech.intune.data.local.database.dao.UserDao
import com.lmptech.intune.model.UserModel

interface UserRepository {
    suspend fun getUserData(): UserModel?

    suspend fun updateUser(user: UserModel)

    suspend fun insertUser(user: UserModel)

    suspend fun deleteUser()
}

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun getUserData(): UserModel? = userDao.getUser()

    override suspend fun insertUser(user: UserModel) = userDao.insertUser(user)

    override suspend fun updateUser(user: UserModel) = userDao.updateUser(user)

    override suspend fun deleteUser() = userDao.deleteUser()
}