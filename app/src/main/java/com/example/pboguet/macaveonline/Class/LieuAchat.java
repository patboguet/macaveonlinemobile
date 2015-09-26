package com.example.pboguet.macaveonline.Class;

/**
 * Created by pboguet on 19/06/15.
 */
public class LieuAchat
{
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

    /**
     * On compare si un objet est de type LieuAchat
     * @param objetAComparer objet à comparer
     * @return boolean Oui ou non
     */
    public boolean equals(Object objetAComparer) {
        boolean res = false;

        if (objetAComparer instanceof LieuAchat)
        {
            LieuAchat lieuAComparer = (LieuAchat) objetAComparer;
            if(lieuAComparer.getId() == this.getId()) {
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
