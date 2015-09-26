package com.example.pboguet.macaveonline.Class;

/**
 * Created by pboguet on 19/06/15.
 */
public class LieuStockage {
    private int id;
    private String nom;

    public LieuStockage(int id, String lieu)
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * On compare si un objet est de type LieuStockage
     * @param objetAComparer objet à comparer
     * @return boolean Oui ou non
     */
    public boolean equals(Object objetAComparer) {
        boolean res = false;
        if (objetAComparer instanceof LieuStockage)
        {
            LieuStockage lieuAComparer = (LieuStockage) objetAComparer;
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
