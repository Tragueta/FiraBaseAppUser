package com.example.moniq.appuserlivros.Activity.Activity.Activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moniq.appuserlivros.R;
import com.google.firebase.auth.FirebaseAuth;

public class TelaInicialActivity extends Activity {

    private Button btnListarLivros;
    private Button btnLivrosFavoritos;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        btnListarLivros = (Button) findViewById(R.id.btnListarLivros);
        btnLivrosFavoritos = (Button) findViewById(R.id.btnLivrosFavoritos);
        mAuth = FirebaseAuth.getInstance();

    }

    // Metodo responsavel por redirecionar o usuario para tela de "Listar Livros"
    public void listarLivros(View v) {
        callActivity(ListaLivrosActivity.class);
    }

    // Metodo responsavel por redirecionar o usuario apra tela de "Livros Favoritos"
    public void livrosFavoritos(View v) {
        callActivity(LivrosFavoritosActivity.class);
    }

    // Metodo responsavel por realizar o logout e redirecionar o usuario para tela de login do app.
    public void sair(View v) {
        mAuth.signOut();
        callActivity(MainActivity.class);
    }

    // Intent usado para o redirecionamento das telas.
    private void callActivity(Class newActivity) {
        Intent newIntent = new Intent(TelaInicialActivity.this,newActivity);
        startActivity(newIntent);
        finish();
    }

}
