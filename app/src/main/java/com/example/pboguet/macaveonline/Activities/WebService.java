package com.example.pboguet.macaveonline.Activities;

import android.app.Activity;
import android.os.Bundle;

import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.Utils.BackTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pboguet on 18/06/15.
 */
public class WebService extends Activity
{
    private static Activity mActivity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        new BackTask().execute("select_vins");
    }

    public static Activity getInstance() {
        return mActivity;
    }

    /**
     * Insertion vin en BDD
     * @param vin Vin à insérer
     */
    public static void insertVin(Vin vin)
    {
        JSONObject vinJson = vinToJson(vin);
        new BackTask().execute("insert", vinJson.toString());
    }

    /**
     * Mise à jour d'un vin
     * @param vin Vin à modifier
     */
    public static void updateVin(Vin vin)
    {
        JSONObject vinJson = vinToJson(vin);
        new BackTask().execute("update", vinJson.toString());

    }

    /**
     * Suppression d'un vin
     * @param vin Vin à supprimer
     */
    public static void deleteVin(Vin vin)
    {
        new BackTask().execute("delete", Long.toString(vin.getIdVin()));
    }

    /**
     * Formatage des données du vin en json
     * @param vin Vin
     * @return json vin au format json
     */
    private static JSONObject vinToJson(Vin vin)
    {
        JSONObject json = new JSONObject();
        try
        {
            // update
            if(vin.getIdVin() > 0)
            {
                json.put("id_vin", vin.getIdVin());
                json.put("FK_type", vin.getType());
                json.put("FK_utilisateur", vin.getUtilisateur());
                json.put("note", vin.getNote());
                json.put("nb_bouteilles", vin.getNbBouteilles());
                json.put("suivi_stock", vin.isSuiviStock());
                json.put("favori", vin.isFavori());
                json.put("prix_achat", vin.getPrixAchat());
                json.put("offert_par", vin.getOffertPar());
                json.put("FK_lieu_stockage", vin.getLieuStockage());
                json.put("FK_lieu_achat", vin.getLieuAchat());
                json.put("conso_partir", vin.getConsoPartir());
                json.put("conso_avant", vin.getConsoAvant());
                json.put("commentaires", vin.getCommentaires());
            }
            // insert
            else
            {
                json.put("nom", vin.getNom());
                json.put("annee", vin.getAnnee());
                json.put("FK_region", vin.getRegion());
                json.put("FK_appellation", vin.getAppellation());
                json.put("FK_type", vin.getType());
                json.put("degre_alcool", vin.getDegreAlcool());
                json.put("FK_lieu_stockage", vin.getLieuStockage());
                json.put("FK_lieu_achat", vin.getLieuAchat());
                json.put("conso_partir", vin.getConsoPartir());
                json.put("conso_avant", vin.getConsoAvant());
                json.put("note", vin.getNote());
                json.put("nb_bouteilles", vin.getNbBouteilles());
                json.put("suivi_stock", vin.isSuiviStock());
                json.put("favori", vin.isFavori());
                json.put("prix_achat", vin.getPrixAchat());
                json.put("offert_par", vin.getOffertPar());
                json.put("commentaires", vin.getCommentaires());
                json.put("FK_utilisateur", vin.getUtilisateur());
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}

