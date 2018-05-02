package com.safeweb.recyclerview.projectbaseretrofit.retrofit.interfaces.model;

import com.safeweb.recyclerview.projectbaseretrofit.model.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioRetrofit {


    @POST("/api/Usuario/ListAllUsuarioAddAtivVincExecutor")
    Call<ArrayList<Usuario>> listAllUsuarios(@Body int value);

}
