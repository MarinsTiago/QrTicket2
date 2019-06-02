package com.example.marin.qrticket.model;

import java.io.Serializable;

/**
 * Created by marin on 06/04/2019.
 */

public class IngressoUsuario implements Serializable{

    private int id;
    private int id_usuario;
    private int id_ingresso;
    private int id_evento;
    private String qrcode;
    private String descricao;
    private float valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
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

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
