package com.example.pboguet.macaveonline.Utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.pboguet.macaveonline.Activities.LoginActivity;
import com.example.pboguet.macaveonline.Class.Appellation;
import com.example.pboguet.macaveonline.Class.LieuAchat;
import com.example.pboguet.macaveonline.Class.LieuStockage;
import com.example.pboguet.macaveonline.Class.Mousseux;
import com.example.pboguet.macaveonline.Class.Plat;
import com.example.pboguet.macaveonline.Class.Region;
import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.Class.VinBlanc;
import com.example.pboguet.macaveonline.Class.VinRose;
import com.example.pboguet.macaveonline.Class.VinRouge;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pboguet on 18/06/15.
 */
public class WebService extends AsyncTask<String, Void, String> {

    private static String URL = "http://www.macaveonline.fr/webservice/";
    private InputStream is;
    private String line;
    private String result;
    private String liste;
    protected String typeSelect;
    public ArrayList<Vin> listeVins = new ArrayList<>();
    public ArrayList<VinBlanc> listeVinsBlanc = new ArrayList<>();
    public ArrayList<VinRouge> listeVinsRouge = new ArrayList<>();
    public ArrayList<VinRose> listeVinsRose = new ArrayList<>();
    public ArrayList<Mousseux> listeMousseux = new ArrayList<>();
    public ArrayList<Region> listeRegion = new ArrayList<>();
    public ArrayList<Appellation> listeAppellation = new ArrayList<>();
    public ArrayList<LieuAchat> listeLieuAchat = new ArrayList<>();
    public ArrayList<LieuStockage> listeLieuStockage = new ArrayList<>();
    public ArrayList<Plat> listePlat = new ArrayList<>();

    @Override
    protected void onPreExecute() {
        //to do whatever you want before execute webservice
        //like progress bar or something like that

    }

    @Override
    protected String doInBackground(String... params) {
        String finUrl = null;
        typeSelect = params[0];
        switch(typeSelect)
        {
            case "select_vins" :  finUrl = "webservice_select_vins.php";
                break;
            case "select_regions" : finUrl = "webservice_select_regions.php";
                break;
            case "select_aoc" : finUrl = "webservice_select_aoc.php";
                break;
            case "select_lieu_achat" : finUrl = "webservice_lieu_achat.php";
                break;
            case "select_lieu_stockage" : finUrl = "webservice_lieu_stockage.php";
                break;
            case "select_plat" : finUrl = "webservice_select_plat.php";
                break;
        }
        URL = URL + finUrl;
        liste = connectWbs(URL);

        return liste;
    }

    @Override
    protected void onPostExecute(String res) {
        //super.onPostExecute(aVoid);
        try {
            JSONArray ar = new JSONArray(res);
            for (int i=0; i<ar.length(); i++){
                JSONObject jsonobject = ar.getJSONObject(i);
                switch(typeSelect)
                {
                    case "select_vins" :
                    {
                        long idVin = Long.parseLong(jsonobject.getString("id_vin"));
                        int region = Integer.parseInt(jsonobject.getString("FK_region"));
                        String type = jsonobject.getString("FK_type");
                        int aoc = Integer.parseInt(jsonobject.getString("FK_appellation"));
                        String nom = jsonobject.getString("nom");
                        int annee = Integer.parseInt(jsonobject.getString("annee"));
                        float degre = Float.parseFloat(jsonobject.getString("degre_alcool"));
                        int conso_partir = Integer.parseInt(jsonobject.getString("conso_partir"));
                        int conso_avant = Integer.parseInt(jsonobject.getString("conso_avant"));
                        int type_plat = Integer.parseInt(jsonobject.getString("FK_type_plat"));
                        int note = Integer.parseInt(jsonobject.getString("note"));
                        int nb_bt = Integer.parseInt(jsonobject.getString("nb_bouteilles"));
                        boolean suivi = Boolean.parseBoolean(jsonobject.getString("suivi_stock"));
                        boolean favori = Boolean.parseBoolean(jsonobject.getString("favori"));
                        float prix = Float.parseFloat(jsonobject.getString("prix_achat"));
                        String offert = jsonobject.getString("offert_par");
                        int lieu_achat = Integer.parseInt(jsonobject.getString("FK_lieu_achat"));
                        int lieu_stockage = Integer.parseInt(jsonobject.getString("FK_lieu_stockage"));
                        String commentaires = jsonobject.getString("commentaires");

                        switch(type)
                        {
                            // Blanc
                            case "1" :
                            {
                                VinBlanc vinB = new VinBlanc(idVin, nom, annee, region, aoc, type, degre, lieu_stockage, lieu_achat, conso_partir, conso_avant, type_plat, note, nb_bt, suivi, favori, prix, offert, commentaires, LoginActivity.myUtilisateur.userId);
                                if(listeVins.indexOf(vinB) == -1)
                                {
                                    listeVinsBlanc.add(vinB);
                                    listeVins.add(vinB);
                                }

                            }
                            break;
                            // Rouge
                            case "2" :
                            {
                                VinRouge vinR = new VinRouge(idVin, nom, annee, region, aoc, type, degre, lieu_stockage, lieu_achat, conso_partir, conso_avant, type_plat, note, nb_bt, suivi, favori, prix, offert, commentaires, LoginActivity.myUtilisateur.userId);
                                if(listeVins.indexOf(vinR) == -1)
                                {
                                    listeVinsRouge.add(vinR);
                                    listeVins.add(vinR);
                                }
                            }
                            break;
                            // Rosé
                            case "3" :
                            {
                                VinRose vinRos = new VinRose(idVin, nom, annee, region, aoc, type, degre, lieu_stockage, lieu_achat, conso_partir, conso_avant, type_plat, note, nb_bt, suivi, favori, prix, offert, commentaires, LoginActivity.myUtilisateur.userId);
                                if(listeVins.indexOf(vinRos) == -1)
                                {
                                    listeVinsRose.add(vinRos);
                                    listeVins.add(vinRos);
                                }
                            }
                            break;
                            // Mousseux
                            case "4" :
                            {
                                Mousseux mousseux = new Mousseux(idVin, nom, annee, region, aoc, type, degre, lieu_stockage, lieu_achat, conso_partir, conso_avant, type_plat, note, nb_bt, suivi, favori, prix, offert, commentaires, LoginActivity.myUtilisateur.userId);
                                if(listeVins.indexOf(mousseux) == -1)
                                {
                                    listeMousseux.add(mousseux);
                                    listeVins.add(mousseux);
                                }
                            }
                        }
                    }

                    break;
                    // liste Régions
                    case "select_regions" :
                    {
                        long idRegion = Long.parseLong(jsonobject.getString("id_region"));
                        String region = jsonobject.getString("region");
                        Region reg = new Region(idRegion, region);
                        if(listeRegion.indexOf(reg) == -1)
                        {
                            listeRegion.add(reg);
                        }
                    }
                    break;
                    // liste Appellations
                    case "select_aoc" :
                    {
                        long idAoc = Long.parseLong(jsonobject.getString("id_appellation"));
                        String aoc = jsonobject.getString("appellation");
                        long idRegion = Long.parseLong(jsonobject.getString("FK_region"));
                        Appellation app = new Appellation(idAoc, aoc, idRegion);
                        if(listeAppellation.indexOf(app) == -1)
                        {
                            listeAppellation.add(app);
                        }
                    }
                    break;
                    // liste Lieu Achat
                    case "select_lieu_achat" :
                    {
                        long idLieu = Long.parseLong(jsonobject.getString("id_lieu_achat"));
                        String lieu = jsonobject.getString("lieu_achat");
                        LieuAchat la = new LieuAchat(idLieu, lieu);
                        if(listeLieuAchat.indexOf(la) == -1)
                        {
                            listeLieuAchat.add(la);
                        }
                    }
                    break;
                    // liste Lieu Stockage
                    case "select_lieu_stockage" :
                    {
                        long idLieu = Long.parseLong(jsonobject.getString("id_lieu_stockage"));
                        String lieu = jsonobject.getString("lieu_stockage");
                        LieuStockage ls = new LieuStockage(idLieu, lieu);
                        if(listeLieuStockage.indexOf(ls) == -1)
                        {
                            listeLieuStockage.add(ls);
                        }
                    }
                    break;
                    // liste Plat
                    case "select_plat" :
                    {
                        long idPlat = Long.parseLong(jsonobject.getString("id_plat"));
                        String plat = jsonobject.getString("type_plat");
                        Plat p = new Plat(idPlat, plat);
                        if(listePlat.indexOf(p) == -1)
                        {
                            listePlat.add(p);
                        }
                    }
                    break;
                }

            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private String connectWbs(String url) {
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            List nameValuePairs = new ArrayList(1);
            nameValuePairs.add(new BasicNameValuePair("idUtilisateur", Long.toString(LoginActivity.myUtilisateur.userId)));
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
