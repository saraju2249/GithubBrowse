package com.example.githubrowse.public_repositories

import android.content.Context

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.githubrowse.GithubBrowserService

import com.example.githubrowse.model.Repo


class RepositoriesViewModel(val api : GithubBrowserService, internal var context: Context, internal var userName: String, internal var key: String) : ViewModel() {

    var itemPagedList: LiveData<PagedList<Repo>>
    var liveDataSource: LiveData<PageKeyedDataSource<Int, Repo>>

    init {


        val itemDataSourceFactory = RepositoriesDataSourceFactory(api,context, userName, key)
        liveDataSource = itemDataSourceFactory.itemLiveDataSource

        val pagedListConfig = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(RepositoriesDataSource.PAGE_SIZE).build()

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)
                .build()
    }

}
