package com.example.githubrowse.details


import androidx.lifecycle.MutableLiveData
import com.example.githubrowse.GithubBrowserService
import com.example.githubrowse.model.Owner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContributionsRepository (val api : GithubBrowserService){
    companion object {
        private var contributionsRepository: ContributionsRepository? = null
        @Synchronized
        @JvmStatic
        fun getInstance(api : GithubBrowserService): ContributionsRepository {
            if (contributionsRepository == null) contributionsRepository = ContributionsRepository(api)
            return contributionsRepository!!
        }
    }

    var loginData =  MutableLiveData<List<Owner>>()


    fun searchByQ(own: String, name:String) : MutableLiveData<List<Owner>>{

        api.getContributions(own,name)
                .enqueue(object : Callback<List<Owner>> {
                    override fun onResponse(call: Call<List<Owner>>, response: Response<List<Owner>>) {

                        response.body()?.let {
                            loginData.value = it
                        }


                    }

                    override fun onFailure(call: Call<List<Owner>>, t: Throwable) {

                    }
                })

        return loginData;
    }



}