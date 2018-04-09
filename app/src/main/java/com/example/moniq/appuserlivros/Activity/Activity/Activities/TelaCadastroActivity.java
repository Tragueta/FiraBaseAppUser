package com.example.moniq.appuserlivros.Activity.Activity.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
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

public class TelaCadastroActivity extends Activity {

    private EditText editNovoEmail;
    private EditText editNovaSenha;
    private Button btnNovoCadastro;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        editNovoEmail = (EditText) findViewById(R.id.editNovoEmail);
        editNovaSenha = (EditText) findViewById(R.id.editNovaSenha);
        btnNovoCadastro = (Button) findViewById(R.id.btnNovoCadastro);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    // Metodo responsavel por realizar o cadastro do email e senha informada pelo usuario
    public void cadastrarNovoUsuario(View v) {

        String novoEmail = editNovoEmail.getText().toString();
        String novaSenha = editNovaSenha.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(novoEmail, novaSenha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Toast.makeText(TelaCadastroActivity.this,
                                    "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else {
                            Log.w("ERRORCAD", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(TelaCadastroActivity.this,
                                                    "Ocorreu um erro durante a autenticação!", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

    }

    // Intent usado para o redirecionamento das telas.
    private void callActivity(Class newActivity) {
        Intent newIntent = new Intent(TelaCadastroActivity.this,newActivity);
        startActivity(newIntent);
        finish();
    }

    // Metodo usado para utilizar o metodo 'callActivity' apos a autenticacao tem sido realizada com sucesso.
    public void updateUI(FirebaseUser currentUser){
        if(currentUser != null)
            callActivity(TelaInicialActivity.class);
        else
            callActivity(TelaCadastroActivity.class);
        Toast.makeText(TelaCadastroActivity.this,
                "Ops... Ocorreu um erro durante a autenticação!", Toast.LENGTH_SHORT).show();
    }

}
