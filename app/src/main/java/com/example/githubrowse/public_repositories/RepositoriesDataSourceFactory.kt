package com.example.githubrowse.public_repositories

import android.content.Context

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.githubrowse.GithubBrowserService

import com.example.githubrowse.model.Repo

class RepositoriesDataSourceFactory(val api : GithubBrowserService, private val context: Context, private val userName: String, private val key: String) : DataSource.Factory<Int, Repo>() {

    val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, Repo>>()

    override fun create(): DataSource<Int, Repo> {
        val itemDataSource = RepositoriesDataSource(api )
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }
}
