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

}
