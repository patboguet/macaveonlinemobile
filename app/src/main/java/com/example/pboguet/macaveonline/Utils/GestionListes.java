package com.example.pboguet.macaveonline.Utils;

import com.example.pboguet.macaveonline.Class.ControleurPrincipal;
import com.example.pboguet.macaveonline.Class.LieuAchat;
import com.example.pboguet.macaveonline.Class.LieuStockage;
import com.example.pboguet.macaveonline.Class.Region;

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
    public static int getIdRegion(String nomRegion)
    {
        for (int i = 0; i < ControleurPrincipal.listeRegion.size(); i++) {
            Region reg = ControleurPrincipal.listeRegion.get(i);
            if(nomRegion.equals(reg.getNom()))
            {
                return reg.getId();
            }
        }
        return 0;
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

    public static String getNomType(String idType)
    {
        String type = "";
        switch (idType)
        {
            case "1" : type = "Blanc";
                break;
            case "2" : type = "Rouge";
                break;
            case "3" : type = "Rosé";
                break;
            case "4" : type = "Mousseux";
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
}
