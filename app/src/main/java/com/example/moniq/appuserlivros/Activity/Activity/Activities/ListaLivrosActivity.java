package com.example.moniq.appuserlivros.Activity.Activity.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.moniq.appuserlivros.Activity.Activity.Classes.Livro;
import com.example.moniq.appuserlivros.Activity.Activity.Classes.LivroAdapter;
import com.example.moniq.appuserlivros.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaLivrosActivity extends Activity {

    private ListView listView;
    private ArrayAdapter<Livro> adapterLivros;
    private ArrayList<Livro> listLivros;
    private ValueEventListener valueEventListener;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_livros);

        listLivros = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listViewLivro);
        adapterLivros = new LivroAdapter(this, listLivros);
        listView.setAdapter(adapterLivros);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("livro");

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listLivros.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()) {
                    Livro novoLivro = dados.getValue(Livro.class);
                    listLivros.add(novoLivro);
                }
                adapterLivros.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

    }

    // Metodos responsaveis pela atualizacao em tempo real da lista de livros

    @Override
    protected void onStop() {
        super.onStop();
        databaseReference.removeEventListener(valueEventListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(valueEventListener);
    }

}
