package com.example.marin.qrticket.model;

/**
 * Created by marin on 06/04/2019.
 */

public class Passeio {
    private int id;
    private String nome;
    private String descricao;
    private int capacidade;
    private Empresa idEmpresa;
    private int dataDevolucao;
    private int flagAprovado;

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

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(int dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getFlagAprovado() {
        return flagAprovado;
    }

    public void setFlagAprovado(int flagAprovado) {
        this.flagAprovado = flagAprovado;
    }
}

