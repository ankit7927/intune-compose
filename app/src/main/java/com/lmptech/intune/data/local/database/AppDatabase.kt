package com.lmptech.intune.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lmptech.intune.data.local.database.dao.ChatDao
import com.lmptech.intune.data.local.database.dao.UserDao
import com.lmptech.intune.model.ChatModel
import com.lmptech.intune.model.UserModel


@Database(entities = [UserModel::class, ChatModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun chatDao(): ChatDao


    companion object {
        private var instance: AppDatabase? = null;
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "user_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}