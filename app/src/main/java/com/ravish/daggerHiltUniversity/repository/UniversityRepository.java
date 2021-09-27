package com.ravish.daggerHiltUniversity.repository;


import androidx.lifecycle.LiveData;

import com.ravish.daggerHiltUniversity.database.UniversityDatabase;
import com.ravish.daggerHiltUniversity.database.UniversityEntity;
import com.ravish.daggerHiltUniversity.model.University;
import com.ravish.daggerHiltUniversity.networking.UniversityApiInterface;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class UniversityRepository {

    private UniversityApiInterface apiInterface;
    private UniversityDatabase database;

    @Inject //dagger oluşturup göndermes için
    public UniversityRepository(UniversityApiInterface apiInterface, UniversityDatabase database){
        this.apiInterface = apiInterface;
        this.database = database;
    }

    public Call<List<University>> getAllUniversities(String country){
        return apiInterface.getAllUniversities(country);
    }

    public void insert(List<UniversityEntity> universities){
        UniversityDatabase.databaseExecutor.execute(() -> {
            database.universityDao().insert(universities);
        });
    }

    public void deleteAllUniversities(){
        UniversityDatabase.databaseExecutor.execute(()->{
            database.universityDao().deleteAllUniversities();
        });
    }

    public LiveData<List<UniversityEntity>> getAllUniversities(){
        return database.universityDao().getAllUniversities();
    }
}
