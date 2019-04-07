package com.example.marin.qrticket.model;

/**
 * Created by marin on 06/04/2019.
 */

public class ItemVenda {

    private int id;
    private int qtde;
    private double subtotal;
    private int flagAprovado;
    private Ingresso idIngresso;
    private Venda idVenda;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getFlagAprovado() {
        return flagAprovado;
    }

    public void setFlagAprovado(int flagAprovado) {
        this.flagAprovado = flagAprovado;
    }

    public Ingresso getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(Ingresso idIngresso) {
        this.idIngresso = idIngresso;
    }

    public Venda getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Venda idVenda) {
        this.idVenda = idVenda;
    }
}
