package com.safeweb.recyclerview.projectbaseretrofit.retrofit.interfaces.model;

import com.safeweb.recyclerview.projectbaseretrofit.model.TAG;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TagRetrofit {

    @POST("/api/Tag/addTag")
    Call<Boolean> addTag(@Body TAG tag);

    @POST("/api/Tag/getAllTag")
    Call<ArrayList<TAG>> getAllTag();

    /*@GET("/api/Tag/addTag")
    Call<Void> addTag();*/
}
