package com.example.githubrowse.local

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubrowse.persistance.ReposDataSource


class ViewModelFactory(private val mDataSource: ReposDataSource) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReposViewModel::class.java)) {
            return ReposViewModel(mDataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
