package com.safeweb.recyclerview.projectbaseretrofit.recyclerView;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.safeweb.recyclerview.projectbaseretrofit.R;
import com.safeweb.recyclerview.projectbaseretrofit.model.Usuario;

import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserViewHolder>{


    private List<Usuario> mListPessoas;
    private ListUserViewHolder.ClickListener mListener;
    // Interface que define as ações

    public ListUserAdapter(List<Usuario> tags, ListUserViewHolder.ClickListener listener){
        this.mListPessoas = tags;
        this.mListener = listener;
    }

    /**
     * Responsável pela criação de linha
     */
    @Override
    public ListUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Obtém o contexto
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Instancia o layout para manipulação dos elementos
        View tagView = inflater.inflate(R.layout.activity_row_user_list, parent, false);

        // Passa a ViewHolder
        return new ListUserViewHolder(tagView);
    }

    /**
     * Responsável por atribuir valores após linha criada
     */
    @Override
    public void onBindViewHolder(ListUserViewHolder holder, int position) {
        Usuario pessoa = this.mListPessoas.get(position);
        holder.bindData(pessoa, mListener);




    }

    @Override
    public int getItemCount() {
        return this.mListPessoas.size();
    }
}
