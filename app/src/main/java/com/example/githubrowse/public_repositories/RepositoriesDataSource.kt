package com.example.githubrowse.public_repositories

import androidx.paging.PageKeyedDataSource
import com.example.githubrowse.GithubBrowserService
import com.example.githubrowse.model.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoriesDataSource(val api :GithubBrowserService) : PageKeyedDataSource<Int, Repo>() {

    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Int>, callback: PageKeyedDataSource.LoadInitialCallback<Int, Repo>) {

        api.getRepo(FIRST_PAGE.toLong())
                .enqueue(object : Callback<List<Repo>> {
                    override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                        if (response.body() != null) {
                            callback.onResult(response.body()!!, null, FIRST_PAGE + 1)
                        }
                    }

                    override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

                    }
                })
    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Repo>) {

//        val api = Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build().create(GithubBrowserService::class.java)
        api.getRepo((FIRST_PAGE + 1).toLong())
                .enqueue(object : Callback<List<Repo>> {
                    override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                        val adjacentKey = if (params.key > 1) params.key - 1 else null
                        if (response.body() != null) {
                            callback.onResult(response.body()!!, adjacentKey)
                        }
                    }

                    override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

                    }
                })
    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Repo>) {
//        val api = Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build().create(GithubBrowserService::class.java)
        api.getRepo(params.key.toLong())
                .enqueue(object : Callback<List<Repo>> {
                    override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                        if (response.body() != null && true) {

                            callback.onResult(response.body()!!, params.key + 1)
                        }
                    }

                    override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

                    }
                })
    }

    companion object {

        val PAGE_SIZE = 50
        private val FIRST_PAGE = 1
    }
}
