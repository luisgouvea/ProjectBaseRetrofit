package com.safeweb.recyclerview.projectbaseretrofit.retrofit.implementantion;

import android.util.Log;

import com.safeweb.recyclerview.projectbaseretrofit.model.TAG;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.APIError;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.ErrorUtils;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.ServiceGenerator;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.interfaces.util.BaseObjectRequest;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.interfaces.model.TagRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TagImplementation implements BaseObjectRequest {

    /**
     * Classes
     */
    private TagRetrofit tagRetrofit;
    private ArrayList<TAG> listTag;
    private APIError error;
    private Boolean insertTag;
    private TAG tag;

    /**
     * CallBacks
     */
    private Call<Boolean> callAddTag;
    private Call<ArrayList<TAG>> callGetAllTag;
    private Call<TAG> callGetTag;

    public TagImplementation() {
        tagRetrofit = ServiceGenerator.getRetrofit().create(TagRetrofit.class);
    }


    @Override
    public APIError verifyResponse(Response response) {
        clearObject();
        if (response != null && response.isSuccessful() && response.code() == 200) {

            String url = response.raw().networkResponse().request().url().host();

            if (url.contains("get")) {
                tag = (TAG) response.body();
            } else if (url.contains("getAll")) {
                listTag = (ArrayList<TAG>) response.body();
            } else if (url.contains("addTag")) {
                insertTag = (Boolean) response.body();
            }

            return null;
        } else {
            // Aconteceu algum erro
            error = ErrorUtils.parseError(response);

            //Log.d("error message", error.message());
            return error;
        }
    }

    @Override
    public String findResponse(Call call, Response response) {
        if (call == callAddTag || call == callGetAllTag) {
            verifyResponse(response);
            if (error != null) {
                return "erro";
            }
            if (call == callAddTag) {
                return "addTag";
            }
            if (call == callGetAllTag) {
                return "getAllTag";
            }
        }
        return "";
    }

    @Override
    public void requestSelectObjectById(Callback callbackGeneric, int id) {

    }


    @Override
    public TAG resultSelectObject() {
        return tag;
    }

    @Override
    public void requestSelectAllObjects(Callback callbackGeneric) {
        Callback<ArrayList<TAG>> callbackListTag = (Callback<ArrayList<TAG>>) callbackGeneric;
        Call<ArrayList<TAG>> call = tagRetrofit.getAllTag();
        call.enqueue(callbackListTag);
        this.callGetAllTag = call;
    }

    @Override
    public ArrayList<TAG> resultSelectAllObject() {
        return listTag;
    }

    @Override
    public void requestInsertObject(Callback callbackGeneric, Object object) {
        Callback<Boolean> callbackListTag = (Callback<Boolean>) callbackGeneric;
        TAG tag = (TAG) object;
        Call<Boolean> call = tagRetrofit.addTag(tag);
        call.enqueue(callbackListTag);
        this.callAddTag = call;
    }

    @Override
    public Boolean resultInsertObject() {
        return insertTag;
    }

    @Override
    public void requestUpdateObject(Callback callbackGeneric, Object object) {

    }

    @Override
    public Boolean resultUpdateObject() {
        return null;
    }

    @Override
    public void requestDeleteObject(Callback callbackGeneric, Object object) {

    }

    @Override
    public Boolean resultDeleteObject() {
        return null;
    }

    @Override
    public APIError resultError() {
        return error;
    }

    @Override
    public void clearObject() {
        tag = null;
        listTag = null;
        error = null;
    }
}
