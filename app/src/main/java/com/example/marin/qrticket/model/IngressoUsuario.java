package com.example.marin.qrticket.model;

/**
 * Created by marin on 06/04/2019.
 */

public class IngressoUsuario {

    private int id;
    private String qrCode;
    private int flagAtivo;
    private int flagAprovado;
    private Usuario idUsuario;
    private Ingresso idIngresso;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public int getFlagAtivo() {
        return flagAtivo;
    }

    public void setFlagAtivo(int flagAtivo) {
        this.flagAtivo = flagAtivo;
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

    public Ingresso getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(Ingresso idIngresso) {
        this.idIngresso = idIngresso;
    }
}
