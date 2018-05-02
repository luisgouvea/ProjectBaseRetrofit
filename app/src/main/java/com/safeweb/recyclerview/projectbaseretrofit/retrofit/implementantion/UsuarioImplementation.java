package com.safeweb.recyclerview.projectbaseretrofit.retrofit.implementantion;


import com.safeweb.recyclerview.projectbaseretrofit.model.Usuario;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.APIError;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.ErrorUtils;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.ServiceGenerator;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.interfaces.util.BaseObjectRequest;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.interfaces.model.UsuarioRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioImplementation implements BaseObjectRequest {

    private UsuarioRetrofit userRetrofit;
    private ArrayList<Usuario> listUsuario;
    private Usuario usuario;
    private APIError error;

    private Call<ArrayList<Usuario>> callGetAllUsuario;
    private Call<Usuario> callGetUsuario;

    public UsuarioImplementation() {
        userRetrofit = ServiceGenerator.createService(UsuarioRetrofit.class);
    }

    @Override
    public void requestSelectAllObjects(Callback callbackGeneric) {
        Callback<ArrayList<Usuario>> callbackListTag = (Callback<ArrayList<Usuario>>) callbackGeneric;
        Call<ArrayList<Usuario>> call = userRetrofit.listAllUsuarios(1);
        call.enqueue(callbackListTag);
        this.callGetAllUsuario = call;
    }

    @Override
    public ArrayList resultSelectAllObject() {
        return null;
    }

    @Override
    public void requestSelectObjectById(Callback callbackGeneric, int id) {

    }

    @Override
    public Usuario resultSelectObject() {
        return usuario;
    }

    @Override
    public void requestInsertObject(Callback callbackGeneric, Object object) {

    }

    @Override
    public Boolean resultInsertObject() {
        return null;
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
    public String findResponse(Call call, Response response) {
        if (call == callGetAllUsuario || call == callGetUsuario) {
            verifyResponse(response);
            if (error != null) {
                return "erro";
            }
            if (call == callGetUsuario) {
                return "getUsuario";
            }
            if (call == callGetAllUsuario) {
                return "getAllUsuario";
            }
        }
        return "";
    }

    @Override
    public APIError verifyResponse(Response response) {
        clearObject();
        if (response != null && response.isSuccessful() && response.code() == 200) {

            String url = response.raw().networkResponse().request().url().host();

            if (url.contains("getUsuario")) {
                usuario = (Usuario) response.body();
            } else if (url.contains("ListAllUsuarioAddAtivVincExecutor")) {
                listUsuario = (ArrayList<Usuario>) response.body();
            }

            return null;
        } else {
            // Aconteceu algum erro
            error = ErrorUtils.parseError(response);

            return error;
        }
    }

    @Override
    public APIError resultError() {
        return error;
    }

    @Override
    public void clearObject() {
        usuario = null;
        listUsuario = null;
        error = null;
    }
}
