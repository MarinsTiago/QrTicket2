package com.example.marin.qrticket.model;

import java.io.Serializable;

/**
 * Created by marin on 02/06/2019.
 */

public class UsuarioShare implements Serializable {

    private int id;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
