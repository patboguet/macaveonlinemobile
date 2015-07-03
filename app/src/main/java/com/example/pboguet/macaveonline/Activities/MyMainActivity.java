package com.example.pboguet.macaveonline.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.pboguet.macaveonline.R;

/**
 * Created by pboguet on 16/04/15.
 */
public class MyMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ma_cave);

        startActivity(new Intent(getBaseContext(), WebService.class));

        /*try {
            // on récupère la liste des vins via webservice
            wbs.execute("select_vins");
            // regions
            wbs.execute("select_regions");
            // appellations
            wbs.execute("select_aoc");
            // lieux d'achat
            wbs.execute("select_lieu_achat");
            // lieux de stockage
            wbs.execute("select_lieu_stockage");
            // plats
            wbs.execute("select_plat");
        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }
}
