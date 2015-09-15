package com.example.pboguet.macaveonline.Utils;

import com.example.pboguet.macaveonline.Class.Appellation;
import com.example.pboguet.macaveonline.Class.ControleurPrincipal;
import com.example.pboguet.macaveonline.Class.LieuAchat;
import com.example.pboguet.macaveonline.Class.LieuStockage;
import com.example.pboguet.macaveonline.Class.Region;
import com.example.pboguet.macaveonline.Class.Vin;

import java.util.ArrayList;

/**
 * Created by pbogu_000 on 26/08/2015.
 */
public class GestionListes {
    public static String getNomRegion(int idRegion)
    {
        for (int i = 0; i < ControleurPrincipal.listeRegion.size(); i++) {
            Region reg = ControleurPrincipal.listeRegion.get(i);
            if(idRegion == reg.getId())
            {
                return reg.getNom();
            }
        }
        return "";
    }

    public static String getNomLieuAchat(int idLieu)
    {
        for (int i = 0; i < ControleurPrincipal.listeLieuAchat.size(); i++) {
            LieuAchat lieu = ControleurPrincipal.listeLieuAchat.get(i);
            if(idLieu == lieu.getId())
            {
                return lieu.getNom();
            }
        }
        return "";
    }

    public static String getNomLieuStockage(int idLieu) {
        for (int i = 0; i < ControleurPrincipal.listeLieuStockage.size(); i++) {
            LieuStockage lieu = ControleurPrincipal.listeLieuStockage.get(i);
            if(idLieu == lieu.getId())
            {
                return lieu.getNom();
            }
        }
        return "";
    }
    public static int getIdLieuStockage(String nomLieu) {
        for (int i = 0; i < ControleurPrincipal.listeLieuStockage.size(); i++) {
            LieuStockage lieu = ControleurPrincipal.listeLieuStockage.get(i);
            if(nomLieu.equals(lieu.getNom()))
            {
                return lieu.getId();
            }
        }
        return 0;
    }

    public static String getNomType(int idType)
    {
        String type = "";
        switch (idType)
        {
            case 1 : type = "Blanc";
                break;
            case 2 : type = "Rouge";
                break;
            case 3 : type = "Rosé";
                break;
            case 4 : type = "Mousseux";
                break;
        }
        return type;
    }

    public static int getIdLieuAchat(String nomLieu) {
        for (int i = 0; i < ControleurPrincipal.listeLieuAchat.size(); i++) {
            LieuAchat lieu = ControleurPrincipal.listeLieuAchat.get(i);
            if(nomLieu.equals(lieu.getNom()))
            {
                return lieu.getId();
            }
        }
        return 0;
    }

    public static Vin getVinById(int id, int type) {
        // retour de l'id dans la liste des Vins par type
        ArrayList liste = null;
        switch(type) {
            // blanc
            case 1 : liste = ControleurPrincipal.listeVinsBlanc;
                break;
            // rouge
            case 2 : liste = ControleurPrincipal.listeVinsRouge;
                break;
            // rose
            case 3 : liste = ControleurPrincipal.listeVinsRose;
                break;
            // mousseux
            case 4 : liste = ControleurPrincipal.listeMousseux;
                break;
            case 0 : liste = ControleurPrincipal.listeVins;
                break;
        }
        for (int i = 0; i < liste.size(); i++)
        {
            Vin vin = (Vin) liste.get(i);
            if(vin.getIdVin() == id)
            {
                return vin;
            }
        }
        return null;
    }

    public static String getNomAppellation(int positionAoc, int idRegion) {
        // Si Mousseux ou Vins d'etrangers, pas d'appellation
        if (idRegion != 6 && idRegion != 12) {
            ArrayList<Appellation> listeAppellations = (ArrayList) ControleurPrincipal.listeRegionAoc.get(idRegion);
            for (int i = 0; i < listeAppellations.size(); i++) {
                Appellation aoc = listeAppellations.get(i);
                if (positionAoc == i) {
                    return aoc.getNom();
                }
            }
            return "";
        }
        return "";
    }


    public static int getIdAppellation(String nomAoc) {
        for (int i = 0; i < ControleurPrincipal.listeAppellation.size(); i++) {
            Appellation aoc = ControleurPrincipal.listeAppellation.get(i);
            if(nomAoc.equals(aoc.getNom()))
            {
                return aoc.getId();
            }
        }
        return 0;
    }
}
