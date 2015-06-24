package com.example.pboguet.macaveonline.Class;

import com.example.pboguet.macaveonline.Utils.Utilisateur;

import java.util.ArrayList;

/**
 * Created by pboguet on 19/06/15.
 */
public class Mousseux extends Vin {
    private String type;
    private ArrayList<Mousseux> liste;

    public Mousseux(int idVin, String nom, int annee, Region region, Appellation appellation, String type, float degreAlcool, LieuStockage lieuStockage, LieuAchat lieuAchat, int consoPartir, int consoAvant, Plat typePlat, int note, int nbBouteilles, boolean suiviStock, boolean favori, float prixAchat, String offertPar, String commentaires, ArrayList<Utilisateur> utilisateur)
    {
        super();
        this.idVin = idVin;
        this.nom = nom;
        this.annee = annee;
        this.region = region;
        this.appellation = appellation;
        this.type = type;
        this.degreAlcool = degreAlcool;
        this.lieuStockage = lieuStockage;
        this.lieuAchat = lieuAchat;
        this.consoPartir = consoPartir;
        this.consoAvant = consoAvant;
        this.typePlat = typePlat;
        this.note = note;
        this.nbBouteilles = nbBouteilles;
        this.suiviStock = suiviStock;
        this.favori = favori;
        this.prixAchat = prixAchat;
        this.offertPar = offertPar;
        this.commentaires = commentaires;
        this.utilisateur = utilisateur;
    }

    public ArrayList<Mousseux> getMousseux()
    {
        return liste;
    }
}
