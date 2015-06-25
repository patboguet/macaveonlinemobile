package com.example.pboguet.macaveonline.Utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.pboguet.macaveonline.Activities.LoginActivity;
import com.example.pboguet.macaveonline.Class.Mousseux;
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
    ArrayList<Vin> listeVins = new ArrayList<Vin>();
    ArrayList<VinBlanc> listeVinsBlanc = new ArrayList<VinBlanc>();
    ArrayList<VinRouge> listeVinsRouge = new ArrayList<VinRouge>();
    ArrayList<VinRose> listeVinsRose = new ArrayList<VinRose>();
    ArrayList<Mousseux> listeMousseux = new ArrayList<Mousseux>();

    @Override
    protected void onPreExecute() {
        //to do whatever you want before execute webservice
        //like progress bar or something like that

    }

    @Override
    protected String doInBackground(String... params) {
        if(params[0] == "select")
        {
            URL = URL + "webservice_select.php";
        }
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
                int idVin = Integer.parseInt(jsonobject.getString("id_vin"));
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
                        listeVinsBlanc.add(vinB);
                        listeVins.add(vinB);
                    }
                        break;
                    // Rouge
                    case "2" :
                    {
                        VinRouge vinR = new VinRouge(idVin, nom, annee, region, aoc, type, degre, lieu_stockage, lieu_achat, conso_partir, conso_avant, type_plat, note, nb_bt, suivi, favori, prix, offert, commentaires, LoginActivity.myUtilisateur.userId);
                        listeVinsRouge.add(vinR);
                        listeVins.add(vinR);
                    }
                    break;
                    // Rosé
                    case "3" :
                    {
                        VinRose vinRos = new VinRose(idVin, nom, annee, region, aoc, type, degre, lieu_stockage, lieu_achat, conso_partir, conso_avant, type_plat, note, nb_bt, suivi, favori, prix, offert, commentaires, LoginActivity.myUtilisateur.userId);
                        listeVinsRose.add(vinRos);
                        listeVins.add(vinRos);
                    }
                    break;
                    // Mousseux
                    case "4" :
                    {
                        Mousseux mousseux = new Mousseux(idVin, nom, annee, region, aoc, type, degre, lieu_stockage, lieu_achat, conso_partir, conso_avant, type_plat, note, nb_bt, suivi, favori, prix, offert, commentaires, LoginActivity.myUtilisateur.userId);
                        listeMousseux.add(mousseux);
                        listeVins.add(mousseux);
                    }
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

            while((line = reader.readLine()) != null) {

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
