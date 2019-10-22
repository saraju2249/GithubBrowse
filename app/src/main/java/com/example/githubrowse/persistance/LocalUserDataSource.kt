package com.example.githubrowse.persistance

import com.example.githubrowse.model.Repo

import io.reactivex.Completable
import io.reactivex.Flowable


class LocalUserDataSource(private val mUserDao: RepoDao) : ReposDataSource {
    override fun delete(repo: Repo?):Completable {

       return repo!!.let {
            mUserDao.delete(it)
        }

    }

    override fun getUser(): Flowable<List<Repo>> {

        return mUserDao.getAll()
    }

//    override fun getUser(): Flowable<Repo> {
//        return mUserDao.getUserById("")
//    }

    override fun insertOrUpdateUser(user: Repo): Completable {
        return mUserDao.insertUser(user)
    }

    override fun deleteAllUsers() {
        mUserDao.deleteAllRepos()
    }
}
