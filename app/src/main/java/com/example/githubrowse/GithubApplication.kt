package com.example.githubrowse

import android.app.Application


open class GithubApplication: Application() {

    override fun onCreate() {
        super.onCreate()
       // configureDi()
    }

//    open fun configureDi() =
//        startKoin(this, appComponent)
//
//    // PUBLIC API ---
//    open fun provideComponent() = appComponent
}