package com.example.pboguet.macaveonline.Class;


/**
 * Created by pboguet on 19/06/15.
 */
public class Appellation {
    private long id;
    private String nom;
    private long idRegion;

    public Appellation(long id, String nom, long idR)
    {
        this.id = id;
        this.nom = nom;
        this.idRegion = idR;
    }

}
