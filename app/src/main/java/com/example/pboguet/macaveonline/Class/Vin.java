package com.example.pboguet.macaveonline.Class;

import com.example.pboguet.macaveonline.Utils.Utilisateur;

import java.util.ArrayList;

/**
 * Created by pboguet on 19/06/15.
 */
public class Vin {
    protected int idVin;
    protected String nom;
    protected int annee;
    protected Region region;
    protected Appellation appellation;
    protected String type;
    protected float degreAlcool;
    protected LieuStockage lieuStockage;
    protected LieuAchat lieuAchat;
    protected int consoPartir;
    protected int consoAvant;
    protected Plat typePlat;
    protected int note;
    protected int nbBouteilles;
    protected boolean suiviStock;
    protected boolean favori;
    protected float prixAchat;
    protected String offertPar;
    protected String commentaires;
    protected ArrayList<Utilisateur> utilisateur;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Appellation getAppellation() {
        return appellation;
    }

    public void setAppellation(Appellation appellation) {
        this.appellation = appellation;
    }

    public LieuStockage getLieuStockage() {
        return lieuStockage;
    }

    public void setLieuStockage(LieuStockage lieuStockage) {
        this.lieuStockage = lieuStockage;
    }

    public LieuAchat getLieuAchat() {
        return lieuAchat;
    }

    public void setLieuAchat(LieuAchat lieuAchat) {
        this.lieuAchat = lieuAchat;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getIdVin() {
        return idVin;
    }

    public void setIdVin(int idVin) {
        this.idVin = idVin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public float getDegreAlcool() {
        return degreAlcool;
    }

    public void setDegreAlcool(float degreAlcool) {
        this.degreAlcool = degreAlcool;
    }

    public int getConsoPartir() {
        return consoPartir;
    }

    public void setConsoPartir(int consoPartir) {
        this.consoPartir = consoPartir;
    }

    public int getConsoAvant() {
        return consoAvant;
    }

    public void setConsoAvant(int consoAvant) {
        this.consoAvant = consoAvant;
    }

    public Plat getTypePlat() {
        return typePlat;
    }

    public void setTypePlat(Plat typePlat) {
        this.typePlat = typePlat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Utilisateur> getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(ArrayList<Utilisateur> utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getNbBouteilles() {
        return nbBouteilles;
    }

    public void setNbBouteilles(int nbBouteilles) {
        this.nbBouteilles = nbBouteilles;
    }

    public boolean isSuiviStock() {
        return suiviStock;
    }

    public void setSuiviStock(boolean suiviStock) {
        this.suiviStock = suiviStock;
    }

    public boolean isFavori() {
        return favori;
    }

    public void setFavori(boolean favori) {
        this.favori = favori;
    }

    public float getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(float prixAchat) {
        this.prixAchat = prixAchat;
    }

    public String getOffertPar() {
        return offertPar;
    }

    public void setOffertPar(String offertPar) {
        this.offertPar = offertPar;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }
}
