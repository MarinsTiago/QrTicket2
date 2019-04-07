package com.example.marin.qrticket.model;

import java.sql.Date;

/**
 * Created by marin on 06/04/2019.
 */

public class Venda {

    private int id;
    private Date data;
    private double total;
    private int flagAprovado;
    private Usuario idUsuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getFlagAprovado() {
        return flagAprovado;
    }

    public void setFlagAprovado(int flagAprovado) {
        this.flagAprovado = flagAprovado;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
}
