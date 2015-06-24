package com.example.pboguet.macaveonline.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.WebService;

/**
 * Created by pboguet on 16/04/15.
 */
public class MyMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ma_cave);

        // on récupère la liste des vins via webservice
        WebService wbs = new WebService();

        try {
            wbs.execute("select");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //String response = wbs.getResponse();



        TabHost tabs=(TabHost)findViewById(R.id.tabHost);

        tabs.setup();
        // on créer les onglets
        createTab(tabs);

    }

    protected void createTab(TabHost tabs)
    {
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
