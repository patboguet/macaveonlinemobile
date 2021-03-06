package com.example.pboguet.macaveonline.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.Appellation;
import com.example.pboguet.macaveonline.Class.ControleurPrincipal;
import com.example.pboguet.macaveonline.Class.LieuAchat;
import com.example.pboguet.macaveonline.Class.LieuStockage;
import com.example.pboguet.macaveonline.Class.Region;
import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.R;

import java.util.ArrayList;

/**
 * Created by pbogu_000 on 26/08/2015.
 */
public class GestionListes
{
    /**
     * Nom d'une région en fonction de son id
     * @param idRegion id de la région
     * @return nom de la région
     */
    public static String getNomRegion(int idRegion)
    {
        for (int i = 0; i < ControleurPrincipal.listeRegion.size(); i++)
        {
            Region reg = ControleurPrincipal.listeRegion.get(i);
            if(idRegion == reg.getId()) {
                return reg.getNom();
            }
        }
        return "";
    }

    public static String getNomLieuAchat(int idLieu)
    {
        for (int i = 0; i < ControleurPrincipal.listeLieuAchat.size(); i++)
        {
            LieuAchat lieu = ControleurPrincipal.listeLieuAchat.get(i);
            if(idLieu == lieu.getId()) {
                return lieu.getNom();
            }
        }
        return "";
    }

    public static String getNomLieuStockage(int idLieu)
    {
        for (int i = 0; i < ControleurPrincipal.listeLieuStockage.size(); i++)
        {
            LieuStockage lieu = ControleurPrincipal.listeLieuStockage.get(i);
            if(idLieu == lieu.getId())  {
                return lieu.getNom();
            }
        }
        return "";
    }

    public static String getNomType(int idType)
    {
        switch (idType)
        {
            case 1 : return "Blanc";
            case 2 : return "Rouge";
            case 3 : return "Rosé";
            case 4 : return "Mousseux";
        }
        return "";
    }

    public static String getNomAppellation(int positionAoc, int idRegion)
    {
        // Si Mousseux ou Vins d'etrangers, pas d'appellation
        if (idRegion != 6 && idRegion != 12)
        {
            ArrayList<Appellation> listeAppellations = (ArrayList) ControleurPrincipal.listeRegionAoc.get(idRegion);
            for (int i = 0; i < listeAppellations.size(); i++)
            {
                Appellation aoc = listeAppellations.get(i);
                if (positionAoc == i) {
                    return aoc.getNom();
                }
            }
            return "";
        }
        return "";
    }

    /**
     * Récupération d'un vin en fonction de son id
     * @param id id du vin
     * @param type type de vin
     * @return Vin
     */
    public static Vin getVinById(int id, int type) {
        // retour de l'id dans la liste des Vins par type
        ArrayList liste = null;
        switch(type)
        {
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
        for (int i = 0; i < liste.size(); i++) {
            Vin vin = (Vin) liste.get(i);
            if(vin.getIdVin() == id){
                return vin;
            }
        }
        return null;
    }

    /**
     * Récupération de l'id d'un lieu de stockage en fonction de son nom
     * @param nomLieu nom du lieu de stockage
     * @return id du lieu
     */
    public static int getIdLieuStockage(String nomLieu)
    {
        for (int i = 0; i < ControleurPrincipal.listeLieuStockage.size(); i++)
        {
            LieuStockage lieu = ControleurPrincipal.listeLieuStockage.get(i);
            if(nomLieu.equals(lieu.getNom())) {
                return lieu.getId();
            }
        }
        return 0;
    }

    public static int getIdLieuAchat(String nomLieu)
    {
        for (int i = 0; i < ControleurPrincipal.listeLieuAchat.size(); i++)
        {
            LieuAchat lieu = ControleurPrincipal.listeLieuAchat.get(i);
            if(nomLieu.equals(lieu.getNom())) {
                return lieu.getId();
            }
        }
        return 0;
    }

    public static int getIdAppellation(String nomAoc)
    {
        for (int i = 0; i < ControleurPrincipal.listeAppellation.size(); i++)
        {
            Appellation aoc = ControleurPrincipal.listeAppellation.get(i);
            if(nomAoc.equals(aoc.getNom())) {
                return aoc.getId();
            }
        }
        return 0;
    }

    /**
     * Création liste de choix avec police personnalisée
     * @param position poisition issu de la listView
     * @param convertView Vue
     * @param c Context
     * @param liste liste des choix
     * @param s Type de contenu dans la liste
     * @return View vue de la liste
     */
    public static View createListe(int position, View convertView, Context c, ArrayList liste, String s)
    {
        View v = convertView;
        if (v == null)
        {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.liste_choix_item, null);
        }
        TextView tvNom = (TextView) v.findViewById(R.id.nom);
        TextView tvId = (TextView) v.findViewById(R.id.id);
        tvNom.setTypeface(Typeface.createFromAsset(c.getAssets(), "fonts/MaiandraGD.ttf"));
        String contenuTvNom = "";
        int contenuTvId = 0;
        switch (s)
        {
            case "type" :
            {
                contenuTvNom = (String) liste.get(position);
                contenuTvId = position;
            }
                break;
            case "aoc" :
            {
                Appellation a = (Appellation) liste.get(position);
                contenuTvNom = a.getNom();
                contenuTvId = a.getId();
            }
                break;
            case "lieuS" :
            {
                LieuStockage ls = (LieuStockage) liste.get(position);
                contenuTvNom = ls.getNom();
                contenuTvId = ls.getId();
            }
                break;
            case "lieuA" :
            {
                LieuAchat la = (LieuAchat) liste.get(position);
                contenuTvNom = la.getNom();
                contenuTvId = la.getId();
            }
                break;
            case "region" :
            {
                Region r = (Region) liste.get(position);
                contenuTvNom = r.getNom();
                contenuTvId = r.getId();
            }
                break;
        }
        tvNom.setText(contenuTvNom);
        tvId.setText(Integer.toString(contenuTvId));

        return v;
    }
}
