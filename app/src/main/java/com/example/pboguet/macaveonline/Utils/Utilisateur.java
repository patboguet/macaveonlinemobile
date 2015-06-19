package com.example.pboguet.macaveonline.Utils;

/**
 * Created by pboguet on 16/04/15.
 */
public class Utilisateur {
    public long userId;
    public String login;
    public String password;
    public String nom;
    public String prenom;
    public String mail;

    public Utilisateur(long userId, String username, String password, String nom, String prenom, String mail){
        this.userId=userId;
        this.login=username;
        this.password=password;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }
}
