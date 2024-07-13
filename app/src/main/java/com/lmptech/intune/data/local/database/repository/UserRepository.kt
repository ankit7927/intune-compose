package com.lmptech.intune.data.local.database.repository

import com.lmptech.intune.data.local.database.dao.UserDao
import com.lmptech.intune.data.model.UserModel

interface UserRepository {
    suspend fun getUserData(): UserModel?

    suspend fun insertUser(user: UserModel)
}

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun getUserData(): UserModel = userDao.getUser()

    override suspend fun insertUser(user: UserModel) = userDao.insertUser(user)
}