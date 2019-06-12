package com.example.marin.qrticket.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by marin on 06/04/2019.
 */

public class Ingresso implements Serializable{

    private int id;
    private String descricao;
    private double valor;
    private int id_evento;

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

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }
}
