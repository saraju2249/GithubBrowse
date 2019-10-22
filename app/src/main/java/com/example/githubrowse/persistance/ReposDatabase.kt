package com.example.githubrowse.persistance


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.githubrowse.model.Owner

import com.example.githubrowse.model.Repo


@Database(entities = [Repo::class, Owner::class], version = 1, exportSchema = false)
abstract class ReposDatabase : RoomDatabase() {

    abstract fun userDao(): RepoDao

    companion object {

        @Volatile
        private var INSTANCE: ReposDatabase? = null

        fun getInstance(context: Context): ReposDatabase? {
            if (INSTANCE == null) {
                synchronized(ReposDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder<ReposDatabase>(context.applicationContext,
                                ReposDatabase::class.java!!, "repos.db")
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}
