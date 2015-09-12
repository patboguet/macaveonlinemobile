package com.example.pboguet.macaveonline.Class;


/**
 * Created by pboguet on 19/06/15.
 */
public class Appellation {
    private int id;
    private String nom;
    private long idRegion;

    public Appellation(int id, String nom, long idR)
    {
        this.id = id;
        this.nom = nom;
        this.idRegion = idR;
    }

    public Appellation(int i, String appellation) {
        this.id = i;
        this.nom = appellation;
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

    public long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(long idRegion) {
        this.idRegion = idRegion;
    }

    public boolean equals(Object objetAComparer) {
        boolean res = false;

        if (objetAComparer instanceof Appellation)
        {
            Appellation aoc = (Appellation) objetAComparer;
            if(aoc.getId() == this.getId())
                res = true;
        }

        return res;
    }
}
