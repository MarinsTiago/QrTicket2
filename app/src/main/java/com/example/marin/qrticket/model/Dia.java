package com.example.marin.qrticket.model;

/**
 * Created by marin on 06/04/2019.
 */

public class Dia {

    private int id;
    private String nome;
    private Passeio idPasseio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Passeio getIdPasseio() {
        return idPasseio;
    }

    public void setIdPasseio(Passeio idPasseio) {
        this.idPasseio = idPasseio;
    }
}