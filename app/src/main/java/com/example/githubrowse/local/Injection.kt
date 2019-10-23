package com.example.githubrowse.local

import android.content.Context

import com.example.githubrowse.persistance.LocalUserDataSource
import com.example.githubrowse.persistance.ReposDataSource
import com.example.githubrowse.persistance.ReposDatabase


object Injection {

    fun provideUserDataSource(context: Context): ReposDataSource {
        val database = ReposDatabase.getInstance(context)
        return LocalUserDataSource(database!!.userDao())
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideUserDataSource(context)
        return ViewModelFactory(dataSource)
    }
}
