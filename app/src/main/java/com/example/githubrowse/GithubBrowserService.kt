package com.example.githubrowse

import com.example.githubrowse.model.Owner
import com.example.githubrowse.model.Repo
import com.example.githubrowse.model.Users
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubBrowserService {


    @GET("/repositories?since=364")
    fun getRepos(
    ): Single<List<Repo>>


    @GET("/repositories?since=364")
    fun getRepos1(@Query("since") page: Int
                   ): Deferred<List<Repo>>

    @GET("repositories")
    abstract fun getRepo(@Query("since") page: Long): Call<List<Repo>>


    @GET("search/users")
    abstract fun getUsers(@Query("q") keyword: String): Call<Users>


    @GET("repos/{owner}/{name}/contributors")
    abstract fun getContributions(@Path("owner") owner: String, @Path("name") name: String ): Call<List<Owner>>


    @GET("users/{login}")
    abstract fun getUser(@Path("login") login: String): Call<Users>
}
