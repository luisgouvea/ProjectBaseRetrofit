package com.safeweb.recyclerview.projectbaseretrofit.retrofit.interfaces;

import com.safeweb.recyclerview.projectbaseretrofit.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserRetrofit {


    @GET("/users")
    Call<ArrayList<User>> listAllUsers();

    @GET("/users/{id}")
    Call<User> getUserById(@Path("id") int idUser);


}
