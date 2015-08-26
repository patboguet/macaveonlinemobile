package com.example.pboguet.macaveonline.Class;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pboguet on 19/06/15.
 */
public class Region {
    private long id;
    private String nom;
    public List<Appellation> listeAppellation;

    public Region(long id, String nom)
    {
        this.listeAppellation = new ArrayList<>();
        this.nom = nom;
        this.id = id;
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Appellation> getListeAppellation() {
        return listeAppellation;
    }

    public void setListeAppellation(List<Appellation> listeAppellation) {
        this.listeAppellation = listeAppellation;
    }
}
