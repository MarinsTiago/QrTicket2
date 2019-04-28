package com.example.marin.qrticket.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by marin on 28/04/2019.
 */

public class Evento {

    private int id;
    private String nome;
    private String descricao;
    private int capacidade;
    private Empresa empresa;
    private Date data;
    private Timestamp horaInicio;
    private Time horaFim;
    private Date dataDevolução;
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

    public Timestamp getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Timestamp horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Time horaFim) {
        this.horaFim = horaFim;
    }

    public Date getDataDevolução() {
        return dataDevolução;
    }

    public void setDataDevolução(Date dataDevolução) {
        this.dataDevolução = dataDevolução;
    }

    public int getFlagAtivo() {
        return flagAtivo;
    }

    public void setFlagAtivo(int flagAtivo) {
        this.flagAtivo = flagAtivo;
    }
}
