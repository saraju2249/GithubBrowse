package com.example.githubrowse.local

import androidx.lifecycle.ViewModel
import com.example.githubrowse.model.Repo
import com.example.githubrowse.persistance.ReposDataSource

import io.reactivex.Completable
import io.reactivex.Flowable


class ReposViewModel(private val mDataSource: ReposDataSource) : ViewModel() {

    private var mUser: Repo? = null


    val userName: Flowable<List<Repo>>
        get() = mDataSource.user


    fun updateRepos(userName: Repo): Completable {
        return mDataSource.insertOrUpdateUser(userName)
    }


    fun delete(repo: Repo): Completable {
        return mDataSource.delete(repo)
    }
}
