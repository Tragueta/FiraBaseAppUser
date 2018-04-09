package com.example.moniq.appuserlivros.Activity.Activity.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.moniq.appuserlivros.R;

import java.util.ArrayList;

public class LivroAdapter extends ArrayAdapter<Livro> {

    private ArrayList<Livro> livros;
    private Context context;

    public LivroAdapter(Context context,  ArrayList<Livro> listLivros) {
        super(context, 0, listLivros);

        this.context = context;
        this.livros = listLivros;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View view = null;

        if(livros != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.listarlivros, parent, false);

            TextView txtNome = (TextView) view.findViewById(R.id.textNomeLivro);
            TextView txtGenero = (TextView) view.findViewById(R.id.textGenero);
            TextView txtAutor = (TextView) view.findViewById(R.id.textAutor);

            Livro novoLivro = livros.get(position);


            txtNome.setText("Nome: " + novoLivro.getNome());
            txtGenero.setText("Genero: " + novoLivro.getGenero());
            txtAutor.setText("Autor: " + novoLivro.getAutor());

        }

        return view;
    }

}
