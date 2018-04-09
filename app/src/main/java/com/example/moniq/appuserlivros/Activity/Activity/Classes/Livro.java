package com.example.moniq.appuserlivros.Activity.Activity.Classes;

public class Livro   {

    private String id;
    private String nome;
    private String genero;
    private String autor;

    public Livro(){

    }

    public Livro(String id, String nome, String genero, String autor) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.autor = autor;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
