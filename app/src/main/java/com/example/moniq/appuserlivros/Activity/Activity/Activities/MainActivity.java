package com.example.moniq.appuserlivros.Activity.Activity.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.moniq.appuserlivros.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends Activity {

    private EditText editEmail;
    private EditText editSenha;
    private FirebaseAuth firebaseAuth;
    private Button btnEntrar;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = (EditText) findViewById(R.id.editEmail);
        editSenha = (EditText) findViewById(R.id.editSenha);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        firebaseAuth = FirebaseAuth.getInstance();

        //Botao responsavel por redirecionar o usuario apra tela de "Cadastrar Usuario"
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callActivity(TelaCadastroActivity.class);
            }
        });

    }

    // Metodo usado para validar o email e senha informados e realizar o login do usuario.
    public void entrar(View v) {
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                            Toast.makeText(MainActivity.this, "Seja bem vindo!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Occorreu um erro dutante a autenticação.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }

    // Intent usado para o redirecionamento das telas.
    private void callActivity(Class newActivity) {
        Intent newIntent = new Intent(MainActivity.this,newActivity);
        startActivity(newIntent);
        finish();
    }

    // Metodo usado para utilizar o metodo 'callActivity' apos a autenticacao tem sido realizada com sucesso.
    public void updateUI(FirebaseUser currentUser){
        if(currentUser != null)
            callActivity(TelaInicialActivity.class);
    }
}
