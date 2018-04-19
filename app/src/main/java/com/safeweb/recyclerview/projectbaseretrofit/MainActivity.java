package com.safeweb.recyclerview.projectbaseretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.safeweb.recyclerview.projectbaseretrofit.model.User;
import com.safeweb.recyclerview.projectbaseretrofit.recyclerView.ListUserAdapter;
import com.safeweb.recyclerview.projectbaseretrofit.recyclerView.ListUserViewHolder;
import com.safeweb.recyclerview.projectbaseretrofit.retrofit.implementantion.UserImplementation;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity<T> extends AppCompatActivity implements ListUserViewHolder.ClickListener, Callback<T> {

    private ViewHolderInfUser mViewHolderInfUser = new ViewHolderInfUser();
    private ListUserAdapter userListAdapter;

    private ListUserViewHolder.ClickListener listener = this;

    private ArrayList<User> listUsers;
    private User user;

    private UserImplementation userImplementantion = new UserImplementation();

    private Callback<T> requestRetrofit = this;

    private Call<ArrayList<User>> callGetAllUser;
    private Call<User> callGetUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.callGetAllUser = userImplementantion.getAllUser(requestRetrofit);
        this.callGetUser = userImplementantion.getUserById(requestRetrofit, 1);
    }

    private void setupRecyclerView() {

        // 1 - Obter a recyclerview
        this.mViewHolderInfUser.mViewRecyclerViewInfUser = findViewById(R.id.recyclerViewInfUser);

        // 2 - Definir adapter passando listagem de users e listener
        userListAdapter = new ListUserAdapter(listUsers, listener);

        this.mViewHolderInfUser.mViewRecyclerViewInfUser.setAdapter(userListAdapter);

        this.mViewHolderInfUser.mViewRecyclerViewInfUser.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));

        // 3 - Definir um layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        this.mViewHolderInfUser.mViewRecyclerViewInfUser.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void textClicked(View v, int position) {
        User user = listUsers.get(position);
        Toast.makeText(getApplicationContext(), "Nome da Pessoa: " + user.getName(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void imageClickedView(View v, int position) {
        Toast.makeText(getApplicationContext(), "Imagem da pessoa", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        try {
            userImplementantion.verifyResponse(response);
            if (call == callGetAllUser) {
                this.listUsers = userImplementantion.resultGetAllUser();
                setupRecyclerView();
            } else if (call == callGetUser) {
                this.user = userImplementantion.resultGetUser();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }

    public class ViewHolderInfUser {
        private RecyclerView mViewRecyclerViewInfUser;
    }
}