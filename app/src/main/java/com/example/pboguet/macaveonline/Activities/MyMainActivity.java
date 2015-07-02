package com.example.pboguet.macaveonline.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.VinAdapter;
import com.example.pboguet.macaveonline.Utils.WebService;

import java.util.ArrayList;

/**
 * Created by pboguet on 16/04/15.
 */
public class MyMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ma_cave);


        WebService wbs = new WebService();

        try {
            // on récupère la liste des vins via webservice
            wbs.execute("select_vins");
            /*/ regions
            wbs.execute("select_regions");
            // appellations
            wbs.execute("select_aoc");
            // lieux d'achat
            wbs.execute("select_lieu_achat");
            // lieux de stockage
            wbs.execute("select_lieu_stockage");
            // plats
            wbs.execute("select_plat");*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        TabHost tabs=(TabHost)findViewById(R.id.tabHost);

        tabs.setup();
        // on créer les onglets
        createTab(tabs);
        tabs.setCurrentTab(1);

        VinAdapter adapter = new VinAdapter(this, R.layout.liste_vins, WebService.listeVins);

        ListView lv = (ListView) findViewById(R.id.listeVinsBlancs);

        lv.setAdapter(adapter);

    }

    protected void createTab(TabHost tabs)
    {
        Intent intentRouges = new Intent().setClass(this, VinsRougesActivity.class);
        TabHost.TabSpec spec=tabs.newTabSpec("tag1");
        spec.setContent(R.id.tabRouge);
        spec.setIndicator("ROUGES");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("tag2");
        spec.setContent(R.id.tabBlanc);
        spec.setIndicator("BLANCS");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("tag3");
        spec.setContent(R.id.tabRose);
        spec.setIndicator("ROSES");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("tag3");
        spec.setContent(R.id.tabMousseux);
        spec.setIndicator("MOUSSEUX");
        tabs.addTab(spec);
    }
}
