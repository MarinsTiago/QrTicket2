package com.example.marin.qrticket.model;

import java.io.Serializable;

/**
 * Created by marin on 06/04/2019.
 */

public class Ingresso implements Serializable{

    private int id;
    private String descricao;
    private double valor;
    private int flagAprovado;
    private Passeio idPasseio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getFlagAprovado() {
        return flagAprovado;
    }

    public void setFlagAprovado(int flagAprovado) {
        this.flagAprovado = flagAprovado;
    }

    public Passeio getIdPasseio() {
        return idPasseio;
    }

    public void setIdPasseio(Passeio idPasseio) {
        this.idPasseio = idPasseio;
    }
}
