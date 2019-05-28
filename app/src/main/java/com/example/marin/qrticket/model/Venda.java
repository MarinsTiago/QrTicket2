package com.example.marin.qrticket.model;

import java.io.Serializable;

/**
 * Created by marin on 06/04/2019.
 */

public class Venda implements Serializable{

    private int id;
    private int id_usuario;
    private int id_ingresso;
    private int quantidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_ingresso() {
        return id_ingresso;
    }

    public void setId_ingresso(int id_ingresso) {
        this.id_ingresso = id_ingresso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
