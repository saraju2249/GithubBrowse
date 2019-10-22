package com.example.githubrowse.public_repositories

import android.content.Context

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubrowse.GithubBrowserService

class RepositoriesViewModelFactory(val api : GithubBrowserService, private val context: Context, private val userName: String, private val key: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepositoriesViewModel(api,context, userName, key) as T
    }
}