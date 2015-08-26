package com.example.pboguet.macaveonline.Class;

import com.example.pboguet.macaveonline.Activities.WebService;
import com.example.pboguet.macaveonline.Utils.Utilisateur;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by pboguet on 19/06/15.
 */
public class Vin {
    protected long idVin;
    protected String nom;
    protected int annee;
    protected int region;
    protected int appellation;
    protected String type;
    protected float degreAlcool;
    protected int lieuStockage;
    protected int lieuAchat;
    protected Date consoPartir;
    protected Date consoAvant;
    protected int typePlat;
    protected int note;
    protected int nbBouteilles;
    protected boolean suiviStock;
    protected boolean favori;
    protected float prixAchat;
    protected String offertPar;
    protected String commentaires;
    protected long utilisateur;
    protected boolean estModifie = false;

    public int getRegion() { return region; }

    public void setRegion(int region) {
        this.region = region;
        estModifie = true;
    }

    public int getAppellation() {
        return appellation;
    }

    public void setAppellation(int appellation) {
        this.appellation = appellation;
        estModifie = true;
    }

    public int getLieuStockage() {
        return lieuStockage;
    }

    public void setLieuStockage(int lieuStockage) {
        this.lieuStockage = lieuStockage;
        estModifie = true;
    }

    public int getLieuAchat() {
        return lieuAchat;
    }

    public void setLieuAchat(int lieuAchat) {
        this.lieuAchat = lieuAchat;
        estModifie = true;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
        estModifie = true;
    }

    public long getIdVin() {
        return idVin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
        estModifie = true;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
        estModifie = true;
    }

    public float getDegreAlcool() {
        return degreAlcool;
    }

    public void setDegreAlcool(float degreAlcool) {
        this.degreAlcool = degreAlcool;
        estModifie = true;
    }

    public Date getConsoPartir() {
        return consoPartir;
    }

    public void setConsoPartir(Date consoPartir) {
        this.consoPartir = consoPartir;
        estModifie = true;
    }

    public Date getConsoAvant() {
        return consoAvant;
    }

    public void setConsoAvant(Date consoAvant) {
        this.consoAvant = consoAvant;
        estModifie = true;
    }

    public int getTypePlat() {
        return typePlat;
    }

    public void setTypePlat(int typePlat) {
        this.typePlat = typePlat;
        estModifie = true;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        estModifie = true;
    }

    public long getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(long utilisateur) {
        this.utilisateur = utilisateur;
        estModifie = true;
    }

    public int getNbBouteilles() {
        return nbBouteilles;
    }

    public void setNbBouteilles(int nbBouteilles) {
        this.nbBouteilles = nbBouteilles;
        estModifie = true;
    }

    public boolean isSuiviStock() {
        return suiviStock;
    }

    public void setSuiviStock(boolean suiviStock) {
        this.suiviStock = suiviStock;
        estModifie = true;
    }

    public boolean isFavori() {
        return favori;
    }

    public void setFavori(boolean favori) {
        this.favori = favori;
        estModifie = true;
    }

    public float getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(float prixAchat) {
        this.prixAchat = prixAchat;
        estModifie = true;
    }

    public String getOffertPar() {
        return offertPar;
    }

    public void setOffertPar(String offertPar) {
        this.offertPar = offertPar;
        estModifie = true;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
        estModifie = true;
    }

    public boolean getModifie() {return estModifie;}

    @Override
    public boolean equals(Object objetAComparer) {
        boolean res = false;

        if (objetAComparer instanceof Vin)
        {
            Vin vinAComparer = (Vin) objetAComparer;
            if(vinAComparer.idVin == this.idVin)
                res = true;
        }

        return res;
    }

    public String getNomRegion(int idRegion)
    {
        for (int i = 0; i < ControleurPrincipal.listeRegion.size(); i++) {
            Region reg = ControleurPrincipal.listeRegion.get(i);
            if(idRegion == reg.getId())
            {
                return reg.getNom();
            }
        }
        return null;
    }
}
