package com.example.githubrowse.local;

import android.content.Context;

import com.example.githubrowse.persistance.LocalUserDataSource;
import com.example.githubrowse.persistance.ReposDataSource;
import com.example.githubrowse.persistance.ReposDatabase;


public class Injection {

    public static ReposDataSource provideUserDataSource(Context context) {
        ReposDatabase database = ReposDatabase.Companion.getInstance(context);
        return new LocalUserDataSource(database.userDao());
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        ReposDataSource dataSource = provideUserDataSource(context);
        return new ViewModelFactory(dataSource);
    }
}
