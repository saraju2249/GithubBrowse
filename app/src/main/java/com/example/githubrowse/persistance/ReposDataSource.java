package com.example.githubrowse.persistance;

import com.example.githubrowse.model.Repo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;


public interface ReposDataSource {

    Flowable<List<Repo>> getUser();

    Completable insertOrUpdateUser(Repo user);

    void deleteAllUsers();

    Completable delete(Repo user);
}
