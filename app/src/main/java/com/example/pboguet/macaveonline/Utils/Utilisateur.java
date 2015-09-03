package com.example.pboguet.macaveonline.Utils;

/**
 * Created by pboguet on 16/04/15.
 */
public class Utilisateur {
    public int userId;
    public String login;
    public String password;
    public String nom;
    public String prenom;
    public String mail;

    public Utilisateur(int userId, String username, String password, String nom, String prenom, String mail){
        this.userId=userId;
        this.login=username;
        this.password=password;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
