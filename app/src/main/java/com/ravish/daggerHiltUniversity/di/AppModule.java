package com.ravish.daggerHiltUniversity.di;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.ravish.daggerHiltUniversity.constants.UniversityUrls;
import com.ravish.daggerHiltUniversity.database.UniversityDatabase;
import com.ravish.daggerHiltUniversity.networking.UniversityApiInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)  // kullanılacak kapsam
public class AppModule {

    @Provides
    @Singleton // bir sınıfın tek bir nesne ile çalışması
    public Retrofit providesRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(UniversityUrls.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public UniversityApiInterface providesUniversityApi(@NonNull Retrofit retrofit){
        return retrofit.create(UniversityApiInterface.class);
    }

    @Provides
    @Singleton
    public UniversityDatabase provideUniversityDatabase(Application application){
        return Room.databaseBuilder(application.getApplicationContext(), UniversityDatabase.class, "university_db")
                .build();
    }
}
