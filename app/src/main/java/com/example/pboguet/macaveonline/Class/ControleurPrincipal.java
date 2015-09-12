package com.example.pboguet.macaveonline.Class;

import android.widget.ListView;

import java.util.ArrayList;

/**
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
    public static ArrayList<Plat> listePlat = new ArrayList<>();
    public static ArrayList<String> menu = new ArrayList<String>(4);
}
