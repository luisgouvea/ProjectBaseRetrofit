package com.safeweb.recyclerview.projectbaseretrofit.recyclerView;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.safeweb.recyclerview.projectbaseretrofit.R;
import com.safeweb.recyclerview.projectbaseretrofit.model.User;

import java.util.List;

public class ListPessoasAdapter extends RecyclerView.Adapter<ListPessoasViewHolder>{


    private List<User> mListPessoas;
    private ListPessoasViewHolder.ClickListener mListener;
    // Interface que define as ações

    public ListPessoasAdapter(List<User> tags, ListPessoasViewHolder.ClickListener listener){
        this.mListPessoas = tags;
        this.mListener = listener;
    }

    /**
     * Responsável pela criação de linha
     */
    @Override
    public ListPessoasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Obtém o contexto
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Instancia o layout para manipulação dos elementos
        View tagView = inflater.inflate(R.layout.activity_row_user_list, parent, false);

        // Passa a ViewHolder
        return new ListPessoasViewHolder(tagView);
    }

    /**
     * Responsável por atribuir valores após linha criada
     */
    @Override
    public void onBindViewHolder(ListPessoasViewHolder holder, int position) {
        User pessoa = this.mListPessoas.get(position);
        holder.bindData(pessoa, mListener);




    }

    @Override
    public int getItemCount() {
        return this.mListPessoas.size();
    }
}
