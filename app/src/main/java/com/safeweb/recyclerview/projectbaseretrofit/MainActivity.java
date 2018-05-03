package com.safeweb.recyclerview.projectbaseretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.safeweb.recyclerview.projectbaseretrofit.model.TAG;
import com.safeweb.recyclerview.projectbaseretrofit.model.Usuario;
import com.safeweb.recyclerview.projectbaseretrofit.recyclerView.ListUserAdapter;
import com.safeweb.recyclerview.projectbaseretrofit.recyclerView.ListUserViewHolder;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.APIError;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.implementantion.TagImplementation;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.implementantion.UsuarioImplementation;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.interfaces.util.RequestObjectRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity<T> extends AppCompatActivity implements ListUserViewHolder.ClickListener, Callback<T>, RequestObjectRetrofit<T> {

    private ViewHolderInfUser mViewHolderInfUser = new ViewHolderInfUser();
    private ListUserAdapter userListAdapter;

    private ListUserViewHolder.ClickListener listener = this;

    private ArrayList<Usuario> listUsuarios;
    private Usuario usuario;
    private TAG tag;

    private UsuarioImplementation usuarioImplementantion = new UsuarioImplementation();
    private TagImplementation tagImplementantion = new TagImplementation();

    private Callback<T> requestRetrofit = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
            Request base com Retrofit
         */
        selectAllUsuarios();
        //addTagNFC(); // TESTE DE API RETORNANDO ERRO
        //selectUsuarioById(); //TODO: NAO FUNCIONAL POR CAUSA DA API

    }

    private void selectAllUsuarios() {
        usuarioImplementantion.requestSelectAllObjects(requestRetrofit);
    }

    private void addTagNFC() {
        TAG tagAdd = new TAG();
        tagAdd.setNome("Direita");
        tagImplementantion.requestInsertObject(requestRetrofit, tagAdd);
    }

    private void selectUsuarioById() {
        //usuarioImplementantion.requestSelectObjectById(requestRetrofit, 1); //TODO: NAO FUNCIONAL POR CAUSA DA API
    }

    private void setupRecyclerView() {

        // 1 - Obter a recyclerview
        this.mViewHolderInfUser.mViewRecyclerViewInfUser = findViewById(R.id.recyclerViewInfUser);

        // 2 - Definir adapter passando listagem de users e listener
        userListAdapter = new ListUserAdapter(listUsuarios, listener);

        this.mViewHolderInfUser.mViewRecyclerViewInfUser.setAdapter(userListAdapter);

        this.mViewHolderInfUser.mViewRecyclerViewInfUser.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));

        // 3 - Definir um layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.mViewHolderInfUser.mViewRecyclerViewInfUser.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void textClicked(View v, int position) {
        Usuario usuario = listUsuarios.get(position);
        Toast.makeText(getApplicationContext(), "Nome da Pessoa: " + usuario.getName(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void imageClickedView(View v, int position) {
        Usuario usuario = listUsuarios.get(position);
        Toast.makeText(getApplicationContext(), "Imagem da pessoa: " + usuario.getName(), Toast.LENGTH_LONG).show();
    }

    /**
     * Request retrofit
     *
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<T> call, retrofit2.Response<T> response) {
        try {
            requestRetrofit(call, response);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Request Failure do retrofit
     *
     * @param call
     * @param t
     */
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
    }


    @Override
    public void requestRetrofit(Call<T> call, Response<T> response) {
        requestUser(call, response);
        requestTag(call, response);
    }

    public void requestUser(Call<T> call, Response<T> response) {
        APIError error = null;
        String typeResponse = usuarioImplementantion.findResponse(call, response);
        if (typeResponse != "") {
            switch (typeResponse) {
                case "erro":
                    error = usuarioImplementantion.resultError();
                    if (error.message() != null) {
                        Toast.makeText(getApplicationContext(), error.message(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_LONG).show();
                    }
                    break;
                case "getAllUsuario":
                    listUsuarios = usuarioImplementantion.resultSelectAllObject();
                    setupRecyclerView();
                    break;
            }
        }
    }

    public void requestTag(Call<T> call, Response<T> response) {
        APIError error = null;
        String typeResponse = tagImplementantion.findResponse(call, response);
        if (typeResponse != "") {
            switch (typeResponse) {
                case "erro":
                    error = tagImplementantion.resultError();
                    if (error.message() != null) {
                        Toast.makeText(getApplicationContext(), error.message(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_LONG).show();
                    }
                    break;
                case "addTag":
                    Boolean executou = tagImplementantion.resultInsertObject();
                    Toast.makeText(getApplicationContext(), "Adicionado a TAG", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    public class ViewHolderInfUser {
        private RecyclerView mViewRecyclerViewInfUser;
    }
}