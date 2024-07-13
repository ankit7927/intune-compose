package com.lmptech.intune.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lmptech.intune.data.model.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY ROWID ASC LIMIT 1")
    fun getUser() : UserModel?

    @Query("SELECT EXISTS(SELECT * FROM users)")
    fun isUserCached() : Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserModel)

    @Update
    suspend fun updateUser(user: UserModel)

    @Query("DELETE FROM users")
    suspend fun deleteUser()
}