package com.lmptech.intune.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lmptech.intune.data.model.UserModel
import com.lmptech.intune.domain.dao.UserCacheDao

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserCacheDao

    companion object {
        private var instance: UserDatabase? = null;
        fun getInstance(context: Context): UserDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }

}