package com.example.pboguet.macaveonline.Class;

import com.example.pboguet.macaveonline.Utils.Utilisateur;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by pboguet on 19/06/15.
 */
public class VinBlanc extends Vin{

    public VinBlanc(int idVin, String nom, int annee, int region, int appellation, int type, float degreAlcool, int lieuStockage, int lieuAchat, String consoPartir, String consoAvant, int typePlat, float note, int nbBouteilles, boolean suiviStock, boolean favori, float prixAchat, String offertPar, String commentaires, int utilisateur)
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

    public VinBlanc() {
        super();
    }
}
