package com.example.pboguet.macaveonline.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pboguet.macaveonline.Class.Appellation;
import com.example.pboguet.macaveonline.Class.ControleurPrincipal;
import com.example.pboguet.macaveonline.Class.LieuAchat;
import com.example.pboguet.macaveonline.Class.LieuStockage;
import com.example.pboguet.macaveonline.Class.Mousseux;
import com.example.pboguet.macaveonline.Class.Plat;
import com.example.pboguet.macaveonline.Class.Region;
import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.Class.VinBlanc;
import com.example.pboguet.macaveonline.Class.VinRose;
import com.example.pboguet.macaveonline.Class.VinRouge;
import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.MousseuxAdapter;
import com.example.pboguet.macaveonline.Utils.VinBlancAdapter;
import com.example.pboguet.macaveonline.Utils.VinRoseAdapter;
import com.example.pboguet.macaveonline.Utils.VinRougeAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pboguet on 18/06/15.
 */
public class WebService extends Activity {

    private static Activity mActivity;
    private InputStream is;
    private String line;
    private String result;
    private String liste;
    protected String typeSelect;
    private int aoc;
    private float degre;
    private Date conso_partir;
    private String commentaires;
    private Date conso_avant;
    private int type_plat;
    private int note;
    private int nb_bt;
    private boolean suivi;
    private boolean favori;
    private float prix;
    private String offert;
    private int lieu_achat;
    private int lieu_stockage;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mActivity = this;
        new BackTask().execute("select_vins");

        // TODO : ne marche pas, à debug
        /*new BackTask().execute("select_regions");
        new BackTask().execute("select_aoc");
        new BackTask().execute("select_lieu_achat");
        new BackTask().execute("select_lieu_stockage");
        new BackTask().execute("select_plat");*/
    }

    public static Activity getInstance() {
        return mActivity;
    }

    public class BackTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute () {
            //to do whatever you want before execute webservice
            // TODO : loading
            //like progress bar or something like that
            super.onPreExecute();
        }

        @Override
        protected String doInBackground (String...params){
            String finUrl = null;
            typeSelect = params[0];
            switch (typeSelect) {
                case "select_vins":
                    finUrl = "webservice_select_vins.php";
                    break;
                case "select_regions":
                    finUrl = "webservice_select_regions.php";
                    break;
                case "select_aoc":
                    finUrl = "webservice_select_aoc.php";
                    break;
                case "select_lieu_achat":
                    finUrl = "webservice_select_lieu_achat.php";
                    break;
                case "select_lieu_stockage":
                    finUrl = "webservice_select_lieu_stockage.php";
                    break;
                case "select_plat":
                    finUrl = "webservice_select_plat.php";
                    break;
            }
            ControleurPrincipal.urlWS = ControleurPrincipal.urlWS + finUrl;
            liste = connectWbs(ControleurPrincipal.urlWS);
            ControleurPrincipal.urlWS = "http://www.macaveonline.fr/webservice/";

            return liste;
        }

        @Override
        protected void onPostExecute (String res){
            //super.onPostExecute(aVoid);
            try {
                JSONArray ar = new JSONArray(res);
                for (int i = 0; i < ar.length(); i++) {
                    JSONObject jsonobject = ar.getJSONObject(i);
                    switch (typeSelect) {
                        case "select_vins": {
                            if (jsonobject.getString("id_vin") != "null") {
                                long idVin = Long.parseLong(jsonobject.getString("id_vin"));
                                int region = Integer.parseInt(jsonobject.getString("FK_region"));
                                String type = jsonobject.getString("FK_type");
                                if (jsonobject.getString("FK_appellation") != "null") {
                                    aoc = Integer.parseInt(jsonobject.getString("FK_appellation"));
                                } else {
                                    aoc = 0;
                                }
                                String nom = jsonobject.getString("nom");
                                int annee = Integer.parseInt(jsonobject.getString("annee"));
                                if (jsonobject.getString("degre_alcool") != "null") {
                                    degre = Integer.parseInt(jsonobject.getString("degre_alcool"));
                                } else {
                                    degre = 0;
                                }
                                if (jsonobject.getString("conso_partir") != "null") {
                                    conso_partir = Date.valueOf(jsonobject.getString("conso_partir"));
                                } else {
                                    conso_partir = null;
                                }
                                if (jsonobject.getString("conso_avant") != "null") {
                                    conso_avant = Date.valueOf(jsonobject.getString("conso_avant"));
                                } else {
                                    conso_avant = null;
                                }
                                if (jsonobject.getString("FK_type_plat") != "null") {
                                    type_plat = Integer.parseInt(jsonobject.getString("FK_type_plat"));
                                } else {
                                    type_plat = 0;
                                }
                                if (jsonobject.getString("note") != "null") {
                                    note = Integer.parseInt(jsonobject.getString("note"));
                                } else {
                                    note = 0;
                                }
                                if (jsonobject.getString("nb_bouteilles") != "null") {
                                    nb_bt = Integer.parseInt(jsonobject.getString("nb_bouteilles"));
                                } else {
                                    nb_bt = 0;
                                }
                                if (jsonobject.getString("suivi_stock") != "null") {
                                    suivi = Boolean.parseBoolean(jsonobject.getString("suivi_stock"));
                                } else {
                                    suivi = false;
                                }
                                if (jsonobject.getString("meilleur_vin") != "null") {
                                    favori = Boolean.parseBoolean(jsonobject.getString("meilleur_vin"));
                                } else {
                                    favori = false;
                                }
                                if (jsonobject.getString("prix_achat") != "null") {
                                    prix = Float.parseFloat(jsonobject.getString("prix_achat"));
                                } else {
                                    prix = 0;
                                }
                                if (jsonobject.getString("offert_par") != "null") {
                                    offert = jsonobject.getString("offert_par");
                                } else {
                                    offert = "";
                                }
                                if (jsonobject.getString("FK_lieu_achat") != "null") {
                                    lieu_achat = Integer.parseInt(jsonobject.getString("FK_lieu_achat"));
                                } else {
                                    lieu_achat = 0;
                                }
                                if (jsonobject.getString("FK_lieu_stockage") != "null") {
                                    lieu_stockage = Integer.parseInt(jsonobject.getString("FK_lieu_stockage"));
                                } else {
                                    lieu_stockage = 0;
                                }
                                if (jsonobject.getString("commentaires") != "null") {
                                    commentaires = jsonobject.getString("commentaires");
                                } else {
                                    commentaires = "";
                                }

                                switch (type) {
                                    // Blanc
                                    case "1": {
                                        VinBlanc vinB = new VinBlanc(idVin, nom, annee, region, aoc, type, degre, lieu_stockage, lieu_achat, conso_partir, conso_avant, type_plat, note, nb_bt, suivi, favori, prix, offert, commentaires, 2);
                                        if (!(ControleurPrincipal.listeVins.contains(vinB))) {
                                            ControleurPrincipal.listeVinsBlanc.add(vinB);
                                            ControleurPrincipal.listeVins.add(vinB);
                                        }
                                    }
                                    break;
                                    // Rouge
                                    case "2": {
                                        VinRouge vinR = new VinRouge(idVin, nom, annee, region, aoc, type, degre, lieu_stockage, lieu_achat, conso_partir, conso_avant, type_plat, note, nb_bt, suivi, favori, prix, offert, commentaires, 2);
                                        if (!(ControleurPrincipal.listeVins.contains(vinR))) {
                                            ControleurPrincipal.listeVinsRouge.add(vinR);
                                            ControleurPrincipal.listeVins.add(vinR);
                                        }
                                    }
                                    break;
                                    // Rosé
                                    case "3": {
                                        VinRose vinRos = new VinRose(idVin, nom, annee, region, aoc, type, degre, lieu_stockage, lieu_achat, conso_partir, conso_avant, type_plat, note, nb_bt, suivi, favori, prix, offert, commentaires, 2);
                                        if(!(ControleurPrincipal.listeVins.contains(vinRos)))
                                        {
                                            ControleurPrincipal.listeVinsRose.add(vinRos);
                                            ControleurPrincipal.listeVins.add(vinRos);
                                        }
                                    }
                                    break;
                                    // Mousseux
                                    case "4": {
                                        Mousseux mousseux = new Mousseux(idVin, nom, annee, region, aoc, type, degre, lieu_stockage, lieu_achat, conso_partir, conso_avant, type_plat, note, nb_bt, suivi, favori, prix, offert, commentaires, 2);
                                        if(!(ControleurPrincipal.listeVins.contains(mousseux)))
                                        {
                                            ControleurPrincipal.listeMousseux.add(mousseux);
                                            ControleurPrincipal.listeVins.add(mousseux);
                                        }
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                        // liste Régions
                        case "select_regions": {
                            long idRegion = Long.parseLong(jsonobject.getString("id_region"));
                            String region = jsonobject.getString("region");
                            Region reg = new Region(idRegion, region);
                            if (ControleurPrincipal.listeRegion.indexOf(reg) == -1) {
                                ControleurPrincipal.listeRegion.add(reg);
                            }
                        }
                        break;
                        // liste Appellations
                        case "select_aoc": {
                            long idAoc = Long.parseLong(jsonobject.getString("id_appellation"));
                            String aoc = jsonobject.getString("appellation");
                            long idRegion = Long.parseLong(jsonobject.getString("FK_region"));
                            Appellation app = new Appellation(idAoc, aoc, idRegion);
                            if (ControleurPrincipal.listeAppellation.indexOf(app) == -1) {
                                ControleurPrincipal.listeAppellation.add(app);
                            }
                        }
                        break;
                        // liste Lieu Achat
                        case "select_lieu_achat": {
                            long idLieu = Long.parseLong(jsonobject.getString("id_lieu_achat"));
                            String lieu = jsonobject.getString("lieu_achat");
                            LieuAchat la = new LieuAchat(idLieu, lieu);
                            if (ControleurPrincipal.listeLieuAchat.indexOf(la) == -1) {
                                ControleurPrincipal.listeLieuAchat.add(la);
                            }
                        }
                        break;
                        // liste Lieu Stockage
                        case "select_lieu_stockage": {
                            long idLieu = Long.parseLong(jsonobject.getString("id_lieu_stockage"));
                            String lieu = jsonobject.getString("lieu_stockage");
                            LieuStockage ls = new LieuStockage(idLieu, lieu);
                            if (ControleurPrincipal.listeLieuStockage.indexOf(ls) == -1) {
                                ControleurPrincipal.listeLieuStockage.add(ls);
                            }
                        }
                        break;
                        // liste Plat
                        case "select_plat": {
                            long idPlat = Long.parseLong(jsonobject.getString("id_plat"));
                            String plat = jsonobject.getString("type_plat");
                            Plat p = new Plat(idPlat, plat);
                            if (ControleurPrincipal.listePlat.indexOf(p) == -1) {
                                ControleurPrincipal.listePlat.add(p);
                            }
                        }
                        break;
                    }
                }
                switch(typeSelect)
                {
                    case "select_vins" : new BackTask().execute("select_regions");
                        break;
                    case "select_regions" : new BackTask().execute("select_aoc");
                        break;
                    case "select_aoc" : new BackTask().execute("select_lieu_achat");
                        break;
                    case "select_lieu_achat" : new BackTask().execute("select_lieu_stockage");
                        break;
                    case "select_lieu_stockage" : new BackTask().execute("select_plat");
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    private String connectWbs(String url) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List nameValuePairs = new ArrayList(1);
            // TODO : erreur avec LoginActivity.myUtilisateur.userId
            //nameValuePairs.add(new BasicNameValuePair("idUtilisateur", Long.toString(LoginActivity.myUtilisateur.userId)));
            nameValuePairs.add(new BasicNameValuePair("idUtilisateur", "2"));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }
        catch (Exception e) {
            Log.e("Webservice 1", e.toString());
        }
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            while((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        }
        catch (Exception e) {
            Log.e("Webservice 2", e.toString());
        }

        return result;
    }




}
