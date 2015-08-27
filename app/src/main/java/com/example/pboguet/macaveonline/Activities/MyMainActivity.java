package com.example.pboguet.macaveonline.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pboguet.macaveonline.Class.ControleurPrincipal;
import com.example.pboguet.macaveonline.Class.FicheVin;
import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.Class.VinRose;
import com.example.pboguet.macaveonline.Class.VinRouge;
import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.MousseuxAdapter;
import com.example.pboguet.macaveonline.Utils.VinBlancAdapter;
import com.example.pboguet.macaveonline.Utils.VinRoseAdapter;
import com.example.pboguet.macaveonline.Utils.VinRougeAdapter;

import java.io.Serializable;
import java.nio.charset.CodingErrorAction;

/**
 * Created by pboguet on 16/04/15.
 */
public class MyMainActivity extends Activity {
    private TabHost tabs;
    private static TextView tvPasVin;
    private TextView tvTriMousseuxNom;
    private TextView tvTriMousseuxRegion;
    private TextView tvTriMousseuxDate;
    private TextView tvTriRougeNom;
    private TextView tvTriRougeRegion;
    private TextView tvTriRougeDate;
    private TextView tvTriBlancNom;
    private TextView tvTriBLancRegion;
    private TextView tvTriBlancDate;
    private TextView tvTriRoseNom;
    private TextView tvTriRoseRegion;
    private TextView tvTriRoseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ma_cave);
        Intent intent = new Intent(this, WebService.class);
        startActivityForResult(intent, 0311);
    }

    @Override
    public void onBackPressed() { super.onBackPressed(); }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(WebService.getInstance() != null) WebService.getInstance().finish();
        if(LoginActivity.getInstance() != null) LoginActivity.getInstance().finish();
    }

    @Override
     protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setContentView(R.layout.ma_cave);

        if(requestCode == 0311) {
            if (resultCode == RESULT_OK) {

                tvTriRougeNom = (TextView) findViewById(R.id.triRougeNom);
                tvTriRougeRegion = (TextView) findViewById(R.id.triRougeRegion);
                tvTriRougeDate = (TextView) findViewById(R.id.triRougeDate);
                tvTriBlancNom = (TextView) findViewById(R.id.triBlancNom);
                tvTriBLancRegion = (TextView) findViewById(R.id.triBlancRegion);
                tvTriBlancDate = (TextView) findViewById(R.id.triBlancDate);
                tvTriRoseNom = (TextView) findViewById(R.id.triRoseNom);
                tvTriRoseRegion = (TextView) findViewById(R.id.triRoseRegion);
                tvTriRoseDate = (TextView) findViewById(R.id.triRoseDate);
                tvTriMousseuxNom = (TextView) findViewById(R.id.triMousseuxNom);
                tvTriMousseuxRegion = (TextView) findViewById(R.id.triMousseuxRegion);
                tvTriMousseuxDate = (TextView) findViewById(R.id.triMousseuxDate);
                tvPasVin = (TextView) findViewById(R.id.tvPasVin);

                tabs = (TabHost) findViewById(R.id.tabHost);
                tabs.setup();
                // on créer les onglets
                createTab(tabs);
                tabs.setCurrentTab(0);

                tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                    @Override
                    public void onTabChanged(String tabId) {
                        resetAffichePasVin();
                        // ROUGES
                        if ("tag1".equals(tabId)) {
                            if (ControleurPrincipal.listeVinsRouge.size() <= 0) {
                                affichePasVin();
                            }
                            else {
                                tvTriRougeNom.setVisibility(View.VISIBLE);
                                tvTriRougeRegion.setVisibility(View.VISIBLE);
                                tvTriRougeDate.setVisibility(View.VISIBLE);
                            }
                        }
                        // BLANCS
                        if ("tag2".equals(tabId)) {
                            if (ControleurPrincipal.listeVinsBlanc.size() <= 0) {
                                affichePasVin();
                            }
                        }
                        // ROSES
                        if ("tag3".equals(tabId)) {
                            if (ControleurPrincipal.listeVinsRose.size() <= 0) {
                                affichePasVin();
                            }
                        }
                        // MOUSSEUX
                        if ("tag4".equals(tabId)) {
                            if (ControleurPrincipal.listeMousseux.size() <= 0) {
                                affichePasVin();
                            }
                        }
                    }
                });
                if (ControleurPrincipal.listeVinsRouge.size() > 0) {
                    VinRougeAdapter rougeAda = new VinRougeAdapter(this, R.layout.liste_vins, ControleurPrincipal.listeVinsRouge);
                    ListView lvR = (ListView) findViewById(R.id.listeVinsRouges);
                    lvR.setAdapter(rougeAda);
                    lvR.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            VinRouge vin = (VinRouge) parent.getItemAtPosition(position);
                            Intent intent = new Intent(view.getContext(), FicheVin.class);
                            intent.putExtra("Vin", (Serializable) vin);
                            startActivity(intent);
                            /*Toast.makeText(getApplicationContext(),
                                    "Click ListItem Number " + position, Toast.LENGTH_LONG)
                                    .show();*/
                        }
                    });
                    tvTriRougeNom.setVisibility(View.VISIBLE);
                    tvTriRougeRegion.setVisibility(View.VISIBLE);
                    tvTriRougeDate.setVisibility(View.VISIBLE);
                } else {
                    MyMainActivity.affichePasVin();
                    tvTriRougeNom.setVisibility(View.GONE);
                    tvTriRougeRegion.setVisibility(View.GONE);
                    tvTriRougeDate.setVisibility(View.GONE);
                }
                if (ControleurPrincipal.listeVinsBlanc.size() > 0) {

                    VinBlancAdapter blancAda = new VinBlancAdapter(this, R.layout.liste_vins, ControleurPrincipal.listeVinsBlanc);
                    ListView lvB = (ListView) findViewById(R.id.listeVinsBlancs);
                    lvB.setAdapter(blancAda);
                    lvB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            Toast.makeText(getApplicationContext(),
                                    "Click ListItem Number " + position, Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                    tvTriBlancNom.setVisibility(View.VISIBLE);
                    tvTriBLancRegion.setVisibility(View.VISIBLE);
                    tvTriBlancDate.setVisibility(View.VISIBLE);
                } else {
                    MyMainActivity.affichePasVin();
                    tvTriBlancNom.setVisibility(View.GONE);
                    tvTriBLancRegion.setVisibility(View.GONE);
                    tvTriBlancDate.setVisibility(View.GONE);
                }
                if (ControleurPrincipal.listeVinsRose.size() > 0) {

                    VinRoseAdapter roseAda = new VinRoseAdapter(this, R.layout.liste_vins, ControleurPrincipal.listeVinsRose);
                    ListView lvRo = (ListView) findViewById(R.id.listeVinsRoses);
                    lvRo.setAdapter(roseAda);
                    lvRo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            Toast.makeText(getApplicationContext(),
                                    "Click ListItem Number " + position, Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                    tvTriRoseNom.setVisibility(View.VISIBLE);
                    tvTriRoseRegion.setVisibility(View.VISIBLE);
                    tvTriRoseDate.setVisibility(View.VISIBLE);
                } else {
                    MyMainActivity.affichePasVin();
                    tvTriRoseNom.setVisibility(View.GONE);
                    tvTriRoseRegion.setVisibility(View.GONE);
                    tvTriRoseDate.setVisibility(View.GONE);
                }
                if (ControleurPrincipal.listeMousseux.size() > 0) {
                    MousseuxAdapter mousseAda = new MousseuxAdapter(this, R.layout.liste_vins, ControleurPrincipal.listeMousseux);
                    ListView lvM = (ListView) findViewById(R.id.listeMousseux);
                    lvM.setAdapter(mousseAda);
                    lvM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            Toast.makeText(getApplicationContext(),
                                    "Click ListItem Number " + position, Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                    tvTriMousseuxNom.setVisibility(View.VISIBLE);
                    tvTriMousseuxRegion.setVisibility(View.VISIBLE);
                    tvTriMousseuxDate.setVisibility(View.VISIBLE);
                }
                else {
                    MyMainActivity.affichePasVin();
                    tvTriMousseuxNom.setVisibility(View.GONE);
                    tvTriMousseuxRegion.setVisibility(View.GONE);
                    tvTriMousseuxDate.setVisibility(View.GONE);
                }
            }
        }
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

        spec=tabs.newTabSpec("tag4");
        spec.setContent(R.id.tabMousseux);
        spec.setIndicator("MOUSSEUX");
        tabs.addTab(spec);
    }

    private void resetAffichePasVin() {
        tvPasVin.setVisibility(View.GONE);
    }

    public static void affichePasVin() {
        tvPasVin.setVisibility(View.VISIBLE);
    }
}
