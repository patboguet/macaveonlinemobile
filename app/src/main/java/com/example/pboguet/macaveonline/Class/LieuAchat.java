package com.example.pboguet.macaveonline.Class;

import java.util.HashMap;

/**
 * Created by pboguet on 19/06/15.
 */
public class LieuAchat {
    private long id;
    private String nom;

    public LieuAchat(long id, String lieu)
    {
        this.id = id;
        this.nom = lieu;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String lieu) {
        this.nom = lieu;
    }
}
