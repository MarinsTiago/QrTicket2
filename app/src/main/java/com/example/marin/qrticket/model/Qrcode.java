package com.example.marin.qrticket.model;

import java.io.Serializable;

/**
 * Created by marin on 06/06/2019.
 */

public class Qrcode implements Serializable {

    private int id_evento;
    private String qrcode;

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
}
