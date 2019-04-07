package com.example.marin.qrticket.model;

import java.sql.Timestamp;

/**
 * Created by marin on 06/04/2019.
 */

public class Horario {

    private int id;
    private Timestamp horaInicio;
    private Timestamp horaFim;
    private Dia idDia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Timestamp horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Timestamp getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Timestamp horaFim) {
        this.horaFim = horaFim;
    }

    public Dia getIdDia() {
        return idDia;
    }

    public void setIdDia(Dia idDia) {
        this.idDia = idDia;
    }
}
