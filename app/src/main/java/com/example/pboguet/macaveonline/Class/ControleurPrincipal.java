package com.example.pboguet.macaveonline.Class;

import android.app.ProgressDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Données statiques de l'application
 * Created by pboguet on 26/08/2015.
 */
public class ControleurPrincipal {
    public static String urlWS = "http://www.macaveonline.fr/webservice/";
    public static ArrayList<Vin> listeVins = new ArrayList<>();
    public static ArrayList<VinBlanc> listeVinsBlanc = new ArrayList<>();
    public static ArrayList<VinRouge> listeVinsRouge = new ArrayList<>();
    public static ArrayList<VinRose> listeVinsRose = new ArrayList<>();
    public static ArrayList<Mousseux> listeMousseux = new ArrayList<>();
    public static ArrayList<Region> listeRegion = new ArrayList<>();
    public static ArrayList<Appellation> listeAppellation = new ArrayList<>();
    public static ArrayList<LieuAchat> listeLieuAchat = new ArrayList<>();
    public static ArrayList<LieuStockage> listeLieuStockage = new ArrayList<>();
    public static ArrayList<String> menu = new ArrayList<String>(3);
    public static Map<Integer, ArrayList<Appellation>> listeRegionAoc = new HashMap<Integer, ArrayList<Appellation>>();
    public static int idVinSupprime = 0;
    public static ProgressDialog loader = null;

    /**
     * Numéro du mois en toute lettre
     * @param num Numéro du mois
     * @param calendrier Calendrier Android ou données du vin
     * @return
     */
    public static String numeroMoisEnLettre(int num, boolean calendrier)
    {
        // mois issu du calendrier Android
        if(calendrier)
        {
            // le calendrier commence à 0 pour les mois
            switch(num)
            {
                case 0 : return "Janvier";
                case 1 : return "Février";
                case 2 : return "Mars";
                case 3 : return "Avril";
                case 4 : return "Mai";
                case 5 : return "Juin";
                case 6 : return "Juillet";
                case 7 : return "Août";
                case 8 : return "Septembre";
                case 9 : return "Octobre";
                case 10 : return "Novembre";
                case 11 : return "Décembre";
                default : return null;
            }
        }
        // mois issu des données
        else
        {
            switch(num)
            {
                case 1 : return "Janvier";
                case 2 : return "Février";
                case 3 : return "Mars";
                case 4 : return "Avril";
                case 5 : return "Mai";
                case 6 : return "Juin";
                case 7 : return "Juillet";
                case 8 : return "Août";
                case 9 : return "Septembre";
                case 10: return "Octobre";
                case 11 : return "Novembre";
                case 12 : return "Décembre";
                default : return null;
            }
        }
    }
}
