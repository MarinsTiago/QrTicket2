package com.example.marin.qrticket.model;

import java.io.Serializable;

/**
 * Created by marin on 06/06/2019.
 */

public class Qrcode implements Serializable {

    private int id;
    private String qrcode;

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
}
