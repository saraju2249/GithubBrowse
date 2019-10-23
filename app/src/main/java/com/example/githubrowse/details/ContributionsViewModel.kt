package com.example.githubrowse.details



import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubrowse.GithubBrowserService
import com.example.githubrowse.model.Owner


class ContributionsViewModel(val context: Context, val api: GithubBrowserService): ViewModel() {

    fun getCtbn(own: String, name: String): LiveData<List<Owner>> {
        return ContributionsRepository.getInstance(api).searchByQ(own, name)
    }


    override fun onCleared() {
        super.onCleared()
    }

    class Factory(val context: Context, val api: GithubBrowserService) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return ContributionsViewModel(context, api) as T
        }
    }
}