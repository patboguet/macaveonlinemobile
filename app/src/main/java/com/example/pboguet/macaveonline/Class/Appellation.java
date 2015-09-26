package com.example.pboguet.macaveonline.Class;


/**
 * Created by pboguet on 19/06/15.
 */
public class Appellation
{
    private int id;
    private String nom;

    public Appellation(int id, String nom)
    {
        this.id = id;
        this.nom = nom;
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

    /**
     * On compare si un objet est de type Appellation
     * @param objetAComparer objet à comparer
     * @return boolean Oui ou non
     */
    public boolean equals(Object objetAComparer)
    {
        boolean res = false;
        if (objetAComparer instanceof Appellation)
        {
            Appellation aoc = (Appellation) objetAComparer;
            if(aoc.getId() == this.getId()) {
                res = true;
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return this.getNom();
    }
}
