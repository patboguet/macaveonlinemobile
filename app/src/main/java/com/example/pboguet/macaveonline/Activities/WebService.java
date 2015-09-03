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
public class WebService extends Activity {

    private static Activity mActivity;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mActivity = this;
        new BackTask(mActivity).execute("select_vins");
    }

    public static Activity getInstance() {
        return mActivity;
    }

    public static void insertVin(Vin vin)
    {
        JSONObject vinJson = vinToJson(vin);
        new BackTask(mActivity).execute("insert", vinJson.toString());
    }

    public static void updateVin(Vin vin)
    {
        JSONObject vinJson = vinToJson(vin);
        new BackTask(mActivity).execute("update", vinJson.toString());

    }

    public static void deleteVin(Vin vin)
    {
        new BackTask(mActivity).execute("delete", Long.toString(vin.getIdVin()));
    }

    private static JSONObject vinToJson(Vin vin) {
        JSONObject json = new JSONObject();
        try {
            if(vin.getIdVin() > 0) {
                json.put("idVin", vin.getIdVin());
            }
            json.put("nom", vin.getNom().replaceAll("\\s", "&nbsp;"));
            json.put("annee", vin.getAnnee());
            json.put("region", vin.getRegion());
            json.put("appellation", vin.getAppellation());
            json.put("type", vin.getType());
            json.put("degreAlcool", vin.getDegreAlcool());
            json.put("lieuStockage", vin.getLieuStockage());
            json.put("lieuAchat", vin.getLieuAchat());
            json.put("consoPartir", vin.getConsoPartir());
            json.put("consoAvant", vin.getConsoAvant());
            json.put("typePlat", vin.getTypePlat());
            json.put("note", vin.getNote());
            json.put("nbBouteilles", vin.getNbBouteilles());
            json.put("suiviStock", vin.isSuiviStock());
            json.put("favori", vin.isFavori());
            json.put("prixAchat", vin.getPrixAchat());
            json.put("offertPar", vin.getOffertPar().replaceAll("\\s", "&nbsp;"));
            json.put("commentaires", vin.getCommentaires().replaceAll("\\s", "&nbsp;"));
            json.put("utilisateur", vin.getUtilisateur());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}

