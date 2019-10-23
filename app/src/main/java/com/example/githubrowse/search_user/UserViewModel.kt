package com.example.githubrowse.search_user


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubrowse.GithubBrowserService
import com.example.githubrowse.model.Users


class UserViewModel(val context: Context, val api: GithubBrowserService): ViewModel() {

    fun search(q: String): LiveData<Users> {
        return UserRepository.getInstance(api).searchByQ(q)
    }


    override fun onCleared() {
        super.onCleared()
    }

    class Factory(val context: Context, val api: GithubBrowserService) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return UserViewModel(context, api) as T
        }
    }
}