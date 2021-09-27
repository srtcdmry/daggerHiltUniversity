package com.ravish.daggerHiltUniversity.networking;


import com.ravish.daggerHiltUniversity.constants.UniversityUrls;
import com.ravish.daggerHiltUniversity.model.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UniversityApiInterface {

    @GET(UniversityUrls.END_POINT_URL)
    Call<List<University>> getAllUniversities(@Query("country") String country);

}
