package com.example.pboguet.macaveonline.Utils;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.pboguet.macaveonline.Activities.FicheVin;
import com.example.pboguet.macaveonline.Activities.MyMainActivity;
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
import com.example.pboguet.macaveonline.Utils.Adapters.VinBlancAdapter;
import com.example.pboguet.macaveonline.Utils.Adapters.VinRougeAdapter;

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
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pboguet on 25/08/2015.
 */
public class BackTask extends AsyncTask<String, Void, String> {
    private InputStream is;
    private String line;
    private String result;
    private String liste;
    private String typeService;
    private int aoc;
    private float degre;
    private String conso_partir;
    private String commentaires;
    private String conso_avant;
    private int type_plat;
    private float note;
    private int nb_bt;
    private boolean suivi;
    private boolean favori;
    private float prix;
    private String offert;
    private int lieu_achat;
    private int lieu_stockage;
    private Activity mActivity;
    private int idUtilisateur;

    public BackTask(Activity a) {
        mActivity = a;
    }
    
    public Activity getInstance() {
        return mActivity;
    }



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
        typeService = params[0];
        switch (typeService) {
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
            case "insert":
                finUrl = "webservice_insert.php";
                break;
            case "update":
                finUrl = "webservice_update.php";
                break;
            case "delete":
                finUrl = "webservice_delete.php";
                break;
        }
        ControleurPrincipal.urlWS = ControleurPrincipal.urlWS + finUrl;
        if(typeService != "insert" && typeService != "update" && typeService != "delete")
        {
            liste = connectWbs(ControleurPrincipal.urlWS, null);
        }
        else
        {
            liste = connectWbs(ControleurPrincipal.urlWS, params[1]);
        }
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
                switch (typeService) {
                    case "select_vins": {
                        if (!jsonobject.getString("id_vin").equals("null")) {
                            int idVin = Integer.parseInt(jsonobject.getString("id_vin"));
                            int region = Integer.parseInt(jsonobject.getString("FK_region"));
                            String type = jsonobject.getString("FK_type");
                            if (!jsonobject.getString("FK_appellation").equals("null")) {
                                aoc = Integer.parseInt(jsonobject.getString("FK_appellation"));
                            } else {
                                aoc = 0;
                            }
                            String nom = jsonobject.getString("nom");
                            int annee = Integer.parseInt(jsonobject.getString("annee"));
                            if (!jsonobject.getString("degre_alcool").equals("null")) {
                                degre = Integer.parseInt(jsonobject.getString("degre_alcool"));
                            } else {
                                degre = 0;
                            }
                            if (!jsonobject.getString("conso_partir").equals("null")) {
                                conso_partir = formatDate(jsonobject.getString("conso_partir"));
                            } else {
                                conso_partir = null;
                            }
                            if (!jsonobject.getString("conso_avant").equals("null")) {
                                conso_avant = formatDate(jsonobject.getString("conso_avant"));
                            } else {
                                conso_avant = null;
                            }
                            if (!jsonobject.getString("FK_type_plat").equals("null")) {
                                type_plat = Integer.parseInt(jsonobject.getString("FK_type_plat"));
                            } else {
                                type_plat = 0;
                            }
                            if (!jsonobject.getString("note").equals("null")) {
                                note = Float.parseFloat(jsonobject.getString("note"));
                            } else {
                                note = 0;
                            }
                            if (!jsonobject.getString("nb_bouteilles").equals("null")) {
                                nb_bt = Integer.parseInt(jsonobject.getString("nb_bouteilles"));
                            } else {
                                nb_bt = 0;
                            }
                            if (jsonobject.getString("suivi_stock").equals("1")) {
                                suivi = true;
                            } else {
                                suivi = false;
                            }
                            if (jsonobject.getString("meilleur_vin").equals("1")) {
                                favori = true;
                            } else {
                                favori = false;
                            }
                            if (!jsonobject.getString("prix_achat").equals("null")) {
                                prix = Float.parseFloat(jsonobject.getString("prix_achat"));
                            } else {
                                prix = 0;
                            }
                            if (!jsonobject.getString("offert_par").equals("null")) {
                                offert = jsonobject.getString("offert_par");
                            } else {
                                offert = "";
                            }
                            if (!jsonobject.getString("FK_lieu_achat").equals("null")) {
                                lieu_achat = Integer.parseInt(jsonobject.getString("FK_lieu_achat"));
                            } else {
                                lieu_achat = 0;
                            }
                            if (!jsonobject.getString("FK_lieu_stockage").equals("null")) {
                                lieu_stockage = Integer.parseInt(jsonobject.getString("FK_lieu_stockage"));
                            } else {
                                lieu_stockage = 0;
                            }
                            if (!jsonobject.getString("commentaires").equals("null")) {
                                commentaires = jsonobject.getString("commentaires");
                            } else {
                                commentaires = "";
                            }

                            if (!jsonobject.getString("FK_utilisateur").equals("null")) {
                                idUtilisateur = Integer.parseInt(jsonobject.getString("FK_utilisateur"));
                            } else {
                                idUtilisateur = 3;
                            }

                            switch (type) {
                                // Blanc
                                case "1": {
                                    VinBlanc vinB = new VinBlanc(idVin, nom, annee, region, aoc, Integer.parseInt(type), degre, lieu_stockage, lieu_achat, conso_partir, conso_avant, type_plat, note, nb_bt, suivi, favori, prix, offert, commentaires, idUtilisateur);
                                    if (!(ControleurPrincipal.listeVins.contains(vinB))) {
                                        ControleurPrincipal.listeVinsBlanc.add(vinB);
                                        ControleurPrincipal.listeVins.add(vinB);
                                    }
                                }
                                break;
                                // Rouge
                                case "2": {
                                    VinRouge vinR = new VinRouge(idVin, nom, annee, region, aoc, Integer.parseInt(type), degre, lieu_stockage, lieu_achat, conso_partir, conso_avant, type_plat, note, nb_bt, suivi, favori, prix, offert, commentaires, idUtilisateur);
                                    if (!(ControleurPrincipal.listeVins.contains(vinR))) {
                                        ControleurPrincipal.listeVinsRouge.add(vinR);
                                        ControleurPrincipal.listeVins.add(vinR);
                                    }
                                }
                                break;
                                // Rosé
                                case "3": {
                                    VinRose vinRos = new VinRose(idVin, nom, annee, region, aoc, Integer.parseInt(type), degre, lieu_stockage, lieu_achat, conso_partir, conso_avant, type_plat, note, nb_bt, suivi, favori, prix, offert, commentaires, idUtilisateur);
                                    if(!(ControleurPrincipal.listeVins.contains(vinRos)))
                                    {
                                        ControleurPrincipal.listeVinsRose.add(vinRos);
                                        ControleurPrincipal.listeVins.add(vinRos);
                                    }
                                }
                                break;
                                // Mousseux
                                case "4": {
                                    Mousseux mousseux = new Mousseux(idVin, nom, annee, region, aoc, Integer.parseInt(type), degre, lieu_stockage, lieu_achat, conso_partir, conso_avant, type_plat, note, nb_bt, suivi, favori, prix, offert, commentaires, idUtilisateur);
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
                        int idRegion = Integer.parseInt(jsonobject.getString("id_region"));
                        String region = jsonobject.getString("region");
                        Region reg = new Region(idRegion, region);
                        if (ControleurPrincipal.listeRegion.indexOf(reg) == -1) {
                            ControleurPrincipal.listeRegion.add(reg);
                        }
                    }
                    break;
                    // liste Appellations
                    case "select_aoc": {
                        int idAoc = Integer.parseInt(jsonobject.getString("id_appellation"));
                        String aoc = jsonobject.getString("appellation");
                        int idRegion = Integer.parseInt(jsonobject.getString("FK_region"));
                        Appellation app = new Appellation(idAoc, aoc, idRegion);
                        if (ControleurPrincipal.listeAppellation.indexOf(app) == -1) {
                            ControleurPrincipal.listeAppellation.add(app);
                        }
                    }
                    break;
                    // liste Lieu Achat
                    case "select_lieu_achat": {
                        int idLieu = Integer.parseInt(jsonobject.getString("id_lieu_achat"));
                        String lieu = jsonobject.getString("lieu_achat");
                        LieuAchat la = new LieuAchat(idLieu, lieu);
                        if (ControleurPrincipal.listeLieuAchat.indexOf(la) == -1) {
                            ControleurPrincipal.listeLieuAchat.add(la);
                        }
                    }
                    break;
                    // liste Lieu Stockage
                    case "select_lieu_stockage": {
                        int idLieu = Integer.parseInt(jsonobject.getString("id_lieu_stockage"));
                        String lieu = jsonobject.getString("lieu_stockage");
                        LieuStockage ls = new LieuStockage(idLieu, lieu);
                        if (ControleurPrincipal.listeLieuStockage.indexOf(ls) == -1) {
                            ControleurPrincipal.listeLieuStockage.add(ls);
                        }
                    }
                    break;
                    /*/ liste Plat
                    case "select_plat": {
                        long idPlat = Long.parseLong(jsonobject.getString("id_plat"));
                        String plat = jsonobject.getString("type_plat");
                        Plat p = new Plat(idPlat, plat);
                        if (ControleurPrincipal.listePlat.indexOf(p) == -1) {
                            ControleurPrincipal.listePlat.add(p);
                        }
                    }
                    break;*/
                    // insérer un vin
                    case "insert": {
                        // TODO gestion retour WS
                    }
                    break;
                    // vin mis à jour
                    case "update": {
                        String msg = jsonobject.getString("message");
                        if(msg.equals("erreur"))
                        {
                            Toast.makeText(mActivity.getApplicationContext(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
                        }
                        else {
                            // on notifie le changement à l'utilisateur
                            Toast.makeText(mActivity.getApplicationContext(), "Le vin a bien été modifié", Toast.LENGTH_LONG).show();
                            // TODO on rafraichi la liste des vins
                        }
                    }
                    break;
                    // retour suppression
                    case "delete": {
                        String msg = jsonobject.getString("idVin");
                        if(!msg.equals("false"))
                        {
                            // on recherche le vin supprimé pour le retirer de la listeVins
                            ArrayList vins = ControleurPrincipal.listeVins;
                            int size = vins.size();
                            long idVin = Long.parseLong(msg);
                            outerloop:
                            for (int j = 0; j < size; j++) {
                                Vin v = (Vin) vins.get(j);
                                long id = v.getIdVin();
                                int type = v.getType();
                                if(id == idVin)
                                {
                                    ControleurPrincipal.listeVins.remove(j);
                                    // on le retire aussi de la listeVins de son type
                                    switch(type)
                                    {
                                        case 1: ControleurPrincipal.listeVinsBlanc.remove(getVin(ControleurPrincipal.listeVinsBlanc, idVin));
                                            break;
                                        case 2: ControleurPrincipal.listeVinsRouge.remove(getVin(ControleurPrincipal.listeVinsRouge, idVin));
                                            break;
                                        case 3: ControleurPrincipal.listeVinsRose.remove(getVin(ControleurPrincipal.listeVinsRose, idVin));
                                            break;
                                        case 4: ControleurPrincipal.listeMousseux.remove(getVin(ControleurPrincipal.listeMousseux, idVin));
                                            break;
                                    }
                                    break outerloop;
                                }
                            }
                            Toast.makeText(mActivity.getApplicationContext(), "Le vin a bien été supprimé", Toast.LENGTH_LONG).show();
                            // TODO rafraichir la liste des vins
                        }
                        else {
                            Toast.makeText(mActivity.getApplicationContext(), "Une erreur est survenue", Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
                }
            }
            switch(typeService)
            {
                case "select_vins" : new BackTask(mActivity).execute("select_regions");
                    break;
                case "select_regions" : new BackTask(mActivity).execute("select_aoc");
                    break;
                case "select_aoc" : new BackTask(mActivity).execute("select_lieu_achat");
                    break;
                case "select_lieu_achat" : new BackTask(mActivity).execute("select_lieu_stockage");
                    break;
                //case "select_lieu_stockage" : new BackTask(mActivity).execute("select_plat");                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mActivity.setResult(Activity.RESULT_OK);
        mActivity.finish();
    }

    // date : yyyy-mm-dd
    private String formatDate(String date) {
        String[] date_explo = date.split("-");
        return date_explo[2]+"-"+date_explo[1]+"-"+date_explo[0];
    }

    protected int getVin(ArrayList listeVins, long idVin) {
        int index = -1;
        outerloop:
        for (int i = 0; i < listeVins.size(); i++) {
            Vin v = (Vin) listeVins.get(i);
            long id = v.getIdVin();
            if(id == idVin)
            {
                index = i;
                break outerloop;
            }
        }
        return index;
    }

    protected String connectWbs(String url, String vin) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List nameValuePairs = new ArrayList(1);
            // TODO : erreur avec LoginActivity.myUtilisateur.userId
            //nameValuePairs.add(new BasicNameValuePair("idUtilisateur", Long.toString(LoginActivity.myUtilisateur.userId)));
            nameValuePairs.add(new BasicNameValuePair("idUtilisateur", "3"));
            nameValuePairs.add(new BasicNameValuePair("donneesVin", vin));
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


