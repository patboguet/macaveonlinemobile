package com.example.pboguet.macaveonline.Activities;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.Appellation;
import com.example.pboguet.macaveonline.Class.ControleurPrincipal;
import com.example.pboguet.macaveonline.Class.LieuStockage;
import com.example.pboguet.macaveonline.Class.Menu;
import com.example.pboguet.macaveonline.Class.Region;
import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.Class.VinBlanc;
import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.Adapters.LieuStockageAdapter;
import com.example.pboguet.macaveonline.Utils.Adapters.RegionAdapter;
import com.example.pboguet.macaveonline.Utils.Adapters.VinAdapter;
import com.example.pboguet.macaveonline.Utils.GestionListes;

import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Patrick on 12/09/2015.
 */
public class Recherche extends Activity {
    private Activity mActivity;
    private EditText champRecherche;
    private Spinner listeRegion;
    private Spinner listeAoc;
    private Spinner listeType;
    private Spinner listeStockage;
    private Button btnRecherche;
    private Button rechercheText;
    private ListView listeVins;
    private TextView noResultat;
    private int region = 0;
    private int aoc = 0;
    private int type = 0;
    private int stockage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.recherche);

        new Menu(getApplicationContext(), this, (ListView) findViewById(R.id.menu));
        //TODO : changer fond item de menu dans la listView

        champRecherche = (EditText) findViewById(R.id.recherche);
        rechercheText = (Button) findViewById(R.id.rechercheText);
        listeRegion = (Spinner) findViewById(R.id.listeRegion);
        listeAoc = (Spinner) findViewById(R.id.listeAoc);
        listeType = (Spinner) findViewById(R.id.listeType);
        listeStockage = (Spinner) findViewById(R.id.listeStockage);
        btnRecherche = (Button) findViewById(R.id.btnRecherche);
        listeVins = (ListView) findViewById(R.id.listeVinsRecherche);
        noResultat = (TextView) findViewById(R.id.aucunVin);

        RegionAdapter regAda = new RegionAdapter(getApplicationContext(), R.layout.liste_choix_item, R.id.nom, ControleurPrincipal.listeRegion);
        regAda.setDropDownViewResource(R.layout.liste_choix_item);
        listeRegion.setAdapter(regAda);
        listeRegion.setOnItemSelectedListener(new onRegionClickListener());
        final ArrayList types = new ArrayList(4);
        types.add(0,"Type de vin");
        types.add(1,"Blanc");
        types.add(2, "Rouge");
        types.add(3, "Rose");
        types.add(4, "Mousseux");
        ArrayAdapter typeAda = new ArrayAdapter<>(getApplicationContext(),R.layout.liste_types,R.id.nomType, types);
        listeType.setAdapter(typeAda);
        LieuStockageAdapter lieuStockAda = new LieuStockageAdapter(getApplicationContext(), R.layout.liste_choix_item,R.id.nom, ControleurPrincipal.listeLieuStockage);
        listeStockage.setAdapter(lieuStockAda);

        listeAoc.setOnItemSelectedListener(new onSpinnerClickListener());
        listeType.setOnItemSelectedListener(new onSpinnerClickListener());
        listeStockage.setOnItemSelectedListener(new onSpinnerClickListener());

        btnRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList listeVinsRes = new ArrayList();
                // recherche uniquement type de vin
                if(region == 0 && aoc == 0 && type > 0 && stockage == 0) {
                    switch(type) {
                        case 1: listeVinsRes = ControleurPrincipal.listeVinsBlanc;
                            break;
                        case 2 : listeVinsRes = ControleurPrincipal.listeVinsRouge;
                            break;
                        case 3 : listeVinsRes = ControleurPrincipal.listeVinsRose;
                            break;
                        case 4 : listeVinsRes = ControleurPrincipal.listeMousseux;
                    }
                }
                // recherche par région
                if(region > 0 && aoc == 0 && type == 0 && stockage == 0)
                {
                    for (int i = 0; i<ControleurPrincipal.listeVins.size();i++)
                    {
                        Vin vi = ControleurPrincipal.listeVins.get(i);
                        int idR = vi.getRegion();
                        if(idR == region)
                        {
                            listeVinsRes.add(vi);
                        }
                    }
                }
                // recherche par région et appellation
                if(region > 0 && aoc > 0 && type == 0 && stockage == 0)
                {
                    for (int i = 0; i<ControleurPrincipal.listeVins.size();i++)
                    {
                        Vin vi = ControleurPrincipal.listeVins.get(i);
                        int idR = vi.getRegion();
                        int idA = vi.getAppellation();
                        if(idR == region && idA == aoc)
                        {
                            listeVinsRes.add(vi);
                        }
                    }
                }
                // recherche par lieux de stockage
                // recherche par nom
                // recherche globale = affichage de la cave

                VinAdapter vinsAda = new VinAdapter(getApplicationContext(), R.layout.liste_vins, listeVinsRes);
                listeVins.setAdapter(vinsAda);
                if(listeVinsRes.size()>0) {
                    if(noResultat.isShown()) {
                        noResultat.setVisibility(View.GONE);
                    }
                }
                else {
                    //vinsAda.notifyDataSetChanged();
                    if(noResultat.getVisibility() == View.GONE) {
                        noResultat.setVisibility(View.VISIBLE);
                    }
                    noResultat.setText("Il n'y a aucun vin pour cette recherche");
                }
            }
        });

    }

    public class onRegionClickListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //parent.getItemAtPosition(position);
            if(position > 0)
            {
                ArrayList listeAppellations = (ArrayList) ControleurPrincipal.listeRegionAoc.get(position);
                if(listeAppellations != null) {
                    ArrayAdapter aocAda = new ArrayAdapter(getApplicationContext(), R.layout.liste_choix_item, R.id.nom, listeAppellations);
                    aocAda.setDropDownViewResource(R.layout.liste_choix_item);
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
                    String a = (String) parent.getItemAtPosition(position);
                    if(!a.equals("Appellation"))
                    {
                        aoc = GestionListes.getIdAppellation(a);
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
}
