package com.example.pboguet.macaveonline.Class;

import java.util.HashMap;

/**
 * Created by pboguet on 19/06/15.
 */
public class LieuAchat {
    private int id;
    private String nom;

    public LieuAchat(int id, String lieu)
    {
        this.id = id;
        this.nom = lieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String lieu) {
        this.nom = lieu;
    }
}
