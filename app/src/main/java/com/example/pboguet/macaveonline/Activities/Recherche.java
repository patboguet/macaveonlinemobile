package com.example.pboguet.macaveonline.Activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.Appellation;
import com.example.pboguet.macaveonline.Class.ControleurPrincipal;
import com.example.pboguet.macaveonline.Class.LieuStockage;
import com.example.pboguet.macaveonline.Class.Menu;
import com.example.pboguet.macaveonline.Class.Region;
import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.Adapters.LieuStockageAdapter;
import com.example.pboguet.macaveonline.Utils.Adapters.RegionAdapter;
import com.example.pboguet.macaveonline.Utils.Adapters.VinAdapter;
import com.example.pboguet.macaveonline.Utils.GestionListes;

import java.util.ArrayList;

/**
 * Created by Patrick on 12/09/2015.
 */
public class Recherche extends Activity {
    private static Activity mActivity;
    private EditText champRecherche;
    private Spinner listeRegion;
    private Spinner listeAoc;
    private Spinner listeType;
    private Spinner listeStockage;
    private Button btnRecherche;
    private ImageButton rechercheText;
    private ListView listeVins;
    private TextView noResultat;
    private int region = 0;
    private int aoc = 0;
    private int type = 0;
    private int stockage = 0;
    private VinAdapter vinsAda = null;
    ArrayList listeVinsRes = new ArrayList();
    private ArrayList types;
    private Context mContext;
    private static Typeface JELLYKA;
    private static Typeface MAIANDRA;

    public static Activity getInstance() {
        return mActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mContext = getApplicationContext();
        setContentView(R.layout.recherche);
        TextView titre = (TextView) findViewById(R.id.titreRecherche);
        JELLYKA = Typeface.createFromAsset(mContext.getAssets(), "fonts/JellykaWonderlandWine.ttf");
        MAIANDRA = Typeface.createFromAsset(mContext.getAssets(), "fonts/MaiandraGD.ttf");
        titre.setTypeface(JELLYKA);

        new Menu(mContext, mActivity, (ListView) findViewById(R.id.menu));

        champRecherche = (EditText) findViewById(R.id.recherche);
        rechercheText = (ImageButton) findViewById(R.id.rechercheText);
        listeRegion = (Spinner) findViewById(R.id.listeRegion);
        listeAoc = (Spinner) findViewById(R.id.listeAoc);
        listeType = (Spinner) findViewById(R.id.listeType);
        listeStockage = (Spinner) findViewById(R.id.listeStockage);
        btnRecherche = (Button) findViewById(R.id.btnRecherche);
        btnRecherche.setTypeface(MAIANDRA);
        listeVins = (ListView) findViewById(R.id.listeVinsRecherche);
        noResultat = (TextView) findViewById(R.id.aucunVin);

        RegionAdapter regAda = new RegionAdapter(mContext, R.layout.liste_choix_item, R.id.nom, ControleurPrincipal.listeRegion);
        regAda.setDropDownViewResource(R.layout.liste_choix_item);
        listeRegion.setAdapter(regAda);
        listeRegion.setOnItemSelectedListener(new onRegionClickListener());
        types = new ArrayList(4);
        types.add(0,"Type de vin");
        types.add(1,"Blanc");
        types.add(2, "Rouge");
        types.add(3, "Rosé");
        types.add(4, "Mousseux");
        ArrayAdapter typeAda = new ArrayAdapter<String>(mContext,R.layout.liste_choix_item,R.id.nom, types) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return GestionListes.createListe(position, convertView, mContext, types, "type");
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                return GestionListes.createListe(position, convertView, mContext, types, "type");
            }
        };
        listeType.setAdapter(typeAda);
        LieuStockageAdapter lieuStockAda = new LieuStockageAdapter(mContext, R.layout.liste_choix_item,R.id.nom, ControleurPrincipal.listeLieuStockage);
        listeStockage.setAdapter(lieuStockAda);

        listeAoc.setOnItemSelectedListener(new onSpinnerClickListener());
        listeType.setOnItemSelectedListener(new onSpinnerClickListener());
        listeStockage.setOnItemSelectedListener(new onSpinnerClickListener());

        btnRecherche.setOnClickListener(new rechercheOnClickListener());
        rechercheText.setOnClickListener(new rechercheTextOnClickListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        int idVS = ControleurPrincipal.idVinSupprime;
        if(idVS > 0) {
            vinsAda.remove(getVin(idVS));
            ControleurPrincipal.idVinSupprime = 0;
        }
        if(vinsAda != null) {
            vinsAda.notifyDataSetChanged();
        }
    }

    protected Vin getVin(int idV) {
        for(int j=0;j<listeVinsRes.size();j++){
            Vin v = (Vin) listeVinsRes.get(j);
            int idVin = v.getIdVin();
            if(idV == idVin) {
                return v;
            }
        }
        return null;
    }

    public class onRegionClickListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position > 0)
            {
                final ArrayList listeAppellations = (ArrayList) ControleurPrincipal.listeRegionAoc.get(position);
                if(listeAppellations != null) {
                    ArrayAdapter aocAda = new ArrayAdapter<String>(mContext, R.layout.liste_choix_item, R.id.nom, listeAppellations) {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            return GestionListes.createListe(position, convertView, mContext, listeAppellations, "aoc");
                        }

                        @Override
                        public View getDropDownView(int position, View convertView, ViewGroup parent) {
                            return GestionListes.createListe(position, convertView, mContext, listeAppellations, "aoc");
                        }
                    };
                    listeAoc.setAdapter(aocAda);
                    listeAoc.setVisibility(View.VISIBLE);
                }
                else {
                    if(listeAoc.isShown())
                    {
                        listeAoc.setVisibility(View.GONE);
                    }
                }
                Region r = (Region) parent.getItemAtPosition(position);
                region = r.getId();
            }
            else {
                if(aoc > 0)
                {
                    aoc = 0;
                }
                if(region > 0)
                {
                    region =0;
                }
                if(listeAoc.isShown())
                {
                    listeAoc.setVisibility(View.GONE);
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class onSpinnerClickListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            int spinner = parent.getId();
            switch(spinner)
            {
                case R.id.listeAoc : {
                    Appellation a = (Appellation) parent.getItemAtPosition(position);
                    String nom = a.getNom();
                    if(!nom.equals("Appellation"))
                    {
                        aoc = GestionListes.getIdAppellation(nom);
                    }
                    else {
                        aoc = 0;
                    }
                }
                break;
                case R.id.listeType : {
                    type = position;
                }
                break;
                case R.id.listeStockage : {
                    LieuStockage ls = (LieuStockage) parent.getItemAtPosition(position);
                    stockage = ls.getId();
                }
                break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class rechercheOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int tailleListe = ControleurPrincipal.listeVins.size();
            listeVinsRes = new ArrayList();
            // recherche uniquement type de vin
            if (region == 0 && aoc == 0 && type > 0 && stockage == 0) {
                switch (type) {
                    case 1:
                        listeVinsRes = ControleurPrincipal.listeVinsBlanc;
                        break;
                    case 2:
                        listeVinsRes = ControleurPrincipal.listeVinsRouge;
                        break;
                    case 3:
                        listeVinsRes = ControleurPrincipal.listeVinsRose;
                        break;
                    case 4:
                        listeVinsRes = ControleurPrincipal.listeMousseux;
                }
            }
            // recherche par région
            if (region > 0 && aoc == 0 && type == 0 && stockage == 0) {
                for (int i = 0; i < tailleListe; i++) {
                    int idR;
                    Vin vi = ControleurPrincipal.listeVins.get(i);
                    idR = vi.getRegion();
                    if (idR == region) {
                        listeVinsRes.add(vi);
                    }
                }
            }
            // recherche par région et appellation
            if (region > 0 && aoc > 0 && type == 0 && stockage == 0) {
                int idR;
                int idA;
                for (int i = 0; i < tailleListe; i++) {
                    Vin vi = ControleurPrincipal.listeVins.get(i);
                    idR = vi.getRegion();
                    idA = vi.getAppellation();
                    if (idR == region && idA == aoc) {
                        listeVinsRes.add(vi);
                    }
                }
            }
            // recherche par région et type de vin
            if(region > 0 && aoc == 0 && type > 0 && stockage == 0) {
                int idR;
                int idT;
                for (int i= 0; i < tailleListe;i++)
                {
                    Vin vi = ControleurPrincipal.listeVins.get(i);
                    idR = vi.getRegion();
                    idT = vi.getType();
                    if(idR == region && idT == type)
                    {
                        listeVinsRes.add(vi);
                    }
                }
            }
            // recherche par région et lieux de stockage
            if(region > 0 && aoc == 0 && type == 0 && stockage > 0) {
                int idR;
                int idS;
                for (int i= 0; i < tailleListe;i++)
                {
                    Vin vi = ControleurPrincipal.listeVins.get(i);
                    idR = vi.getRegion();
                    idS = vi.getLieuStockage();
                    if(idR == region && idS == stockage)
                    {
                        listeVinsRes.add(vi);
                    }
                }
            }
            // recherche par région, appellation et type de vin
            if(region > 0 && aoc > 0 && type > 0 && stockage == 0) {
                int idR;
                int idA;
                int idT;
                for (int i= 0; i < tailleListe;i++)
                {
                    Vin vi = ControleurPrincipal.listeVins.get(i);
                    idR = vi.getRegion();
                    idT = vi.getType();
                    idA = vi.getAppellation();
                    if(idR == region && idT == type && idA == aoc)
                    {
                        listeVinsRes.add(vi);
                    }
                }
            }
            // recherche par région, appellation et lieux de stockage
            if(region > 0 && aoc > 0 && type == 0 && stockage > 0) {
                int idR;
                int idA;
                int idS;
                for (int i= 0; i < tailleListe;i++)
                {
                    Vin vi = ControleurPrincipal.listeVins.get(i);
                    idR = vi.getRegion();
                    idA = vi.getAppellation();
                    idS = vi.getLieuStockage();
                    if(idR == region && idA == aoc && idS == stockage)
                    {
                        listeVinsRes.add(vi);
                    }
                }
            }
            // recherche par région, type et lieux de stockage
            if(region > 0 && aoc == 0 && type > 0 && stockage > 0) {
                int idR;
                int idT;
                int idS;
                for (int i= 0; i < tailleListe;i++)
                {
                    Vin vi = ControleurPrincipal.listeVins.get(i);
                    idR = vi.getRegion();
                    idT = vi.getType();
                    idS = vi.getLieuStockage();
                    if(idR == region && idT == type && idS == stockage)
                    {
                        listeVinsRes.add(vi);
                    }
                }
            }
            // recherche par type et lieux de stockage
            if(region == 0 && aoc == 0 && type > 0 && stockage > 0) {
                int idT;
                int idS;
                for (int i= 0; i < tailleListe;i++)
                {
                    Vin vi = ControleurPrincipal.listeVins.get(i);
                    idS = vi.getLieuStockage();
                    idT = vi.getType();
                    if(idS == stockage && idT == type)
                    {
                        listeVinsRes.add(vi);
                    }
                }
            }
            // recherche par lieux de stockage
            if (region == 0 && aoc == 0 && type == 0 && stockage > 0) {
                int idS;
                for (int i = 0; i < tailleListe; i++) {
                    Vin vi = ControleurPrincipal.listeVins.get(i);
                    idS = vi.getLieuStockage();
                    if (idS == stockage) {
                        listeVinsRes.add(vi);
                    }
                }
            }
            // recherche par tous critères
            if (region > 0 && aoc > 0 && type > 0 && stockage > 0) {
                int idR;
                int idA;
                int idT;
                int idS;
                for (int i = 0; i < tailleListe; i++) {
                    Vin vi = ControleurPrincipal.listeVins.get(i);
                    idR = vi.getRegion();
                    idA = vi.getAppellation();
                    idT = vi.getType();
                    idS = vi.getLieuStockage();
                    if (idR == region && idA == aoc && idT == type && idS == stockage) {
                        listeVinsRes.add(vi);
                    }
                }
            }
            // recherche globale = affichage de la cave
            if(region == 0 && aoc == 0 && type == 0 && stockage == 0)
            {
                listeVinsRes = ControleurPrincipal.listeVins;
            }

            vinsAda = new VinAdapter(mContext, R.layout.liste_vins, listeVinsRes);
            listeVins.setAdapter(vinsAda);
            if (listeVinsRes.size() > 0) {
                if (noResultat.isShown()) {
                    noResultat.setVisibility(View.GONE);
                }
            } else {
                if (noResultat.getVisibility() == View.GONE) {
                    noResultat.setVisibility(View.VISIBLE);
                }
                noResultat.setText("Il n'y a aucun vin pour cette recherche");
            }
        }
    }

    private class rechercheTextOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // on cache le clavier
            InputMethodManager imm = (InputMethodManager)getSystemService(mContext.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            int tailleListe = ControleurPrincipal.listeVins.size();
            listeVinsRes = new ArrayList();
            String nomVin = champRecherche.getText().toString().toLowerCase();
            for (int i = 0; i < tailleListe; i++)
            {
                Vin vi = ControleurPrincipal.listeVins.get(i);
                String nom = vi.getNom().toLowerCase();
                if(nom.contains(nomVin)) {
                    listeVinsRes.add(vi);
                }
            }
            vinsAda = new VinAdapter(mContext, R.layout.liste_vins, listeVinsRes);
            listeVins.setAdapter(vinsAda);
            if (listeVinsRes.size() > 0) {
                if (noResultat.isShown()) {
                    noResultat.setVisibility(View.GONE);
                }
            } else {
                if (noResultat.getVisibility() == View.GONE) {
                    noResultat.setVisibility(View.VISIBLE);
                }
                noResultat.setText("Il n'y a aucun vin pour cette recherche");
            }
        }
    }
}
