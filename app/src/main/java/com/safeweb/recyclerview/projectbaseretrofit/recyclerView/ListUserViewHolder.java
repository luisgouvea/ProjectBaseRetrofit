package com.safeweb.recyclerview.projectbaseretrofit.recyclerView;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.safeweb.recyclerview.projectbaseretrofit.R;
import com.safeweb.recyclerview.projectbaseretrofit.model.User;

public class ListUserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    // Elemento de interface
    private TextView nomePessoa;
    private ImageView photoPessoa;
    public ClickListener clickListner;

    /**
     * Construtor
     */
    public ListUserViewHolder(View itemView) {
        super(itemView);
        this.nomePessoa = itemView.findViewById(R.id.text_name_pessoa);
        /*this.photoPessoa = itemView.findViewById(R.id.image_pessoa);*/
    }

    /**
     * Atribui valores aos elementos
     */
    public void bindData(final User pessoa, ClickListener listner) {
        clickListner = listner;
        // Altera valor
        this.nomePessoa.setText(pessoa.getName());
        this.nomePessoa.setOnClickListener(this);
        /*this.photoPessoa.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.text_name_pessoa) {
            clickListner.textClicked(view, getAdapterPosition());
        } else {
            clickListner.imageClickedView(view, getAdapterPosition());
        }
    }


    public interface ClickListener {

        void textClicked(View v, int position);

        void imageClickedView(View v, int position);
    }
}