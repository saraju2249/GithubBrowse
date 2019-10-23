package com.example.githubrowse.persistance

import androidx.room.*
import com.example.githubrowse.model.Repo
import io.reactivex.Completable
import io.reactivex.Flowable


@Dao
interface RepoDao {


    @Query("SELECT * FROM repo")
    fun getAll(): Flowable<List<Repo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(repo: Repo): Completable


    @Query("DELETE FROM repo")
    fun deleteAllRepos()

    @Delete
    fun delete(repo: Repo) : Completable
}
