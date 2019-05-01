package com.example.marin.qrticket.model;

import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by marin on 28/04/2019.
 */

public class Evento implements Serializable {

    private int id;
    private String nome;
    private String descricao;
    private int capacidade;
    private Empresa empresa;
    private Date data;
    private TimeZone horaInicio;
    private TimeZone horaFim;
    private Date dataDevolucao;
    private int flagAtivo;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public TimeZone getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(TimeZone horaInicio) {
        this.horaInicio = horaInicio;
    }

    public TimeZone getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(TimeZone horaFim) {
        this.horaFim = horaFim;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getFlagAtivo() {
        return flagAtivo;
    }

    public void setFlagAtivo(int flagAtivo) {
        this.flagAtivo = flagAtivo;
    }
}
