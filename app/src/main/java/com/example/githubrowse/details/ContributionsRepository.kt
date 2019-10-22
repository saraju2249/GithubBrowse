package com.example.githubrowse.details


import androidx.lifecycle.MutableLiveData
import com.example.githubrowse.GithubBrowserService
import com.example.githubrowse.model.Contributions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContributionsRepository (val api : GithubBrowserService){
//    companion object {
//        private var loginRepository: ContributionsRepository? = null
//        private var context: Context?=null
//        @Synchronized
//        @JvmStatic
//        fun getInstance(context: Context): ContributionsRepository {
//            this.context=context
//            if (loginRepository == null) loginRepository = ContributionsRepository()
//            return loginRepository!!
//        }
//    }

    var loginData =  MutableLiveData<List<Contributions>>()


    fun searchByQ(own: String, name:String) : MutableLiveData<List<Contributions>>{

//        val api = Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build().create(GithubBrowserService::class.java)
        api.getContributions(own,name)
                .enqueue(object : Callback<List<Contributions>> {
                    override fun onResponse(call: Call<List<Contributions>>, response: Response<List<Contributions>>) {

                        response.body()?.let {
                            loginData.value = it
                        }


                    }

                    override fun onFailure(call: Call<List<Contributions>>, t: Throwable) {

                    }
                })

        return loginData;
    }



}