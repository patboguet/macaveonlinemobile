package com.example.pboguet.macaveonline.Activities;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.Appellation;
import com.example.pboguet.macaveonline.Class.ControleurPrincipal;
import com.example.pboguet.macaveonline.Class.LieuAchat;
import com.example.pboguet.macaveonline.Class.LieuStockage;
import com.example.pboguet.macaveonline.Class.Menu;
import com.example.pboguet.macaveonline.Class.Region;
import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.Adapters.MousseuxAdapter;
import com.example.pboguet.macaveonline.Utils.Adapters.VinBlancAdapter;
import com.example.pboguet.macaveonline.Utils.Adapters.VinRoseAdapter;
import com.example.pboguet.macaveonline.Utils.Adapters.VinRougeAdapter;

import java.util.ArrayList;
import java.util.ResourceBundle;

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
    private static Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        if(ControleurPrincipal.menu.size() < 4) {
            ControleurPrincipal.menu.add(0, "Ma Cave");
            ControleurPrincipal.menu.add(1, "Recherche");
            ControleurPrincipal.menu.add(2, "Ajouter");
            //ControleurPrincipal.menu.add(3, "Suivi");
            //ControleurPrincipal.menu.add(4, "Paramètres");
        }
        initControleuPrincipal();
        setContentView(R.layout.ma_cave);
        new Menu(getApplicationContext(), this, (ListView) findViewById(R.id.menu));

        Intent intent = new Intent(this, WebService.class);
        startActivityForResult(intent, 0311);
    }

    // Pour l'affichage des listes à sélectionner
    private void initControleuPrincipal() {
        Region reg = new Region(0, "Région");
        ControleurPrincipal.listeRegion.add(0,reg);
        LieuStockage ls = new LieuStockage(0, "Lieu Stockage");
        ControleurPrincipal.listeLieuStockage.add(0,ls);
        LieuAchat la = new LieuAchat(0, "Lieu Achat");
        ControleurPrincipal.listeLieuAchat.add(0,la);
    }

    public static Activity getInstance() {
        return mActivity;
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
     protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //setContentView(R.layout.ma_cave);
        afficheCave(requestCode, resultCode, data);
    }

    private void afficheCave(int requestCode, int resultCode, Intent data) {
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
