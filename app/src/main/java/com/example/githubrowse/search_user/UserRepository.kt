package com.example.githubrowse.search_user


import androidx.lifecycle.MutableLiveData
import com.example.githubrowse.GithubBrowserService
import com.example.githubrowse.model.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository (val api : GithubBrowserService){
    companion object {
        private var userRepository: UserRepository? = null
        @Synchronized
        @JvmStatic
        fun getInstance( api : GithubBrowserService): UserRepository {
            if (userRepository == null) userRepository = UserRepository(api)
            return userRepository!!
        }
    }

    var loginData =  MutableLiveData<Users>()


    fun searchByQ(q: String) : MutableLiveData<Users>{

        api.getUsers(q)
                .enqueue(object : Callback<Users> {
                    override fun onResponse(call: Call<Users>, response: Response<Users>) {

                        response.body()?.let {
                            loginData.value = it
                        }


                    }

                    override fun onFailure(call: Call<Users>, t: Throwable) {

                    }
                })

        return loginData;
    }



}