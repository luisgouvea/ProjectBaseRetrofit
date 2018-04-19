package com.safeweb.recyclerview.projectbaseretrofit.retrofit.implementantion;


import com.safeweb.recyclerview.projectbaseretrofit.model.User;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.interfaces.BaseUrlRetrofit;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.interfaces.UserRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserImplementation <T> {

    private UserRetrofit userRetrofit;
    private ArrayList<User> listUser;
    private User user;

    public UserImplementation(){
        userRetrofit = BaseUrlRetrofit.retrofit.create(UserRetrofit.class);
    }

    public Call<ArrayList<User>> getAllUser(Callback<T> callbackGeneric){
        Callback<ArrayList<User>> callbackListUser  = (Callback<ArrayList<User>>) callbackGeneric; // CAST para o tipo de retorno **Lista de User**
        Call<ArrayList<User>> call = userRetrofit.listAllUsers();
        call.enqueue(callbackListUser);
        return call;
    }

    public Call<User> getUserById(Callback<T> callbackGeneric, int idUser){
        Callback<User> callbackUser  = (Callback<User>) callbackGeneric;    // CAST para o tipo de retorno **User**
        Call<User> call = userRetrofit.getUserById(idUser);
        call.enqueue(callbackUser);
        return call;
    }

    public void verifyResponse(Response<T> response){
        if(response != null && response.isSuccessful() && response.code() == 200){
            if(response.body() instanceof ArrayList){
                ArrayList<T> list = (ArrayList<T>) response.body();
                if(list.get(0) instanceof User){
                    listUser = (ArrayList<User>) list;
                }
            }
            else if(response.body() instanceof User){
                user = (User) response.body();
            }
        }
    }

    public User resultGetUser(){
        return user;
    }

    public ArrayList<User> resultGetAllUser(){
        return listUser;
    }
}
