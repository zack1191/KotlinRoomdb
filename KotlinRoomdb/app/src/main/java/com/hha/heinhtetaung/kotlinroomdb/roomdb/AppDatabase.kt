package com.hha.heinhtetaung.kotlinroomdb.roomdb

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.hha.heinhtetaung.kotlinroomdb.roomdb.Daos.ToDoListDao
import com.hha.heinhtetaung.kotlinroomdb.roomdb.entities.ToDoListEntity

@Database(entities = [ToDoListEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun toDoListDao(): ToDoListDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }

    }
}