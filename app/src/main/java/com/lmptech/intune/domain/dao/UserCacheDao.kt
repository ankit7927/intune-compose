package com.lmptech.intune.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.lmptech.intune.data.model.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserCacheDao {
    @Query("SELECT * FROM users ORDER BY ROWID ASC LIMIT 1")
    fun getUser() : Flow<UserModel>

    @Insert
    suspend fun insertUser(user: UserModel)

    @Update
    suspend fun updateUser(user: UserModel)

    @Delete
    suspend fun deleteUser(user: UserModel)
}