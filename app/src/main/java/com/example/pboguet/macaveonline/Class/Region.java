package com.example.pboguet.macaveonline.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pboguet on 19/06/15.
 */
public class Region {
    private int id;
    private String nom;
    public List<String> listeAppellation;

    public Region(int id, String nom)
    {
        this.listeAppellation = new ArrayList<>();
        this.nom = nom;
        this.id = id;
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<String> getListeAppellation() {
        return listeAppellation;
    }

    @Override
    public boolean equals(Object objetAComparer) {
        boolean res = false;

        if (objetAComparer instanceof Region)
        {
            Region regionAComparer = (Region) objetAComparer;
            if(regionAComparer.getId() == this.getId())
                res = true;
        }

        return res;
    }
}
