package com.example.pboguet.macaveonline.Class;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Activities.AjoutVin;
import com.example.pboguet.macaveonline.Activities.MyMainActivity;
import com.example.pboguet.macaveonline.Activities.Recherche;
import com.example.pboguet.macaveonline.R;

/**
 * Created by Patrick on 12/09/2015.
 */
public class Menu {
    public Menu(Context c, final Activity a, ListView l) {
        l = (ListView) l.findViewById(R.id.menu);
        final ArrayAdapter<String> menuAda = new ArrayAdapter<String>(c,R.layout.nom_menu, R.id.nomMenu,ControleurPrincipal.menu);
        l.setAdapter(menuAda);

        // Gestion du menu
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvMenu = (TextView) view.findViewById(R.id.nomMenu);
                String valMenu = tvMenu.getText().toString();
                Intent intent = null;
                switch (valMenu) {
                    case "Ma Cave":
                        intent = new Intent(a, MyMainActivity.class);
                        break;
                    case "Recherche":
                        intent = new Intent(a, Recherche.class);
                        break;
                    case "Ajouter":
                        intent = new Intent(a, AjoutVin.class);
                        break;
                    /*case "Suivi" : act.setContentView(R.layout.liste_vins);
                    break;
                    case "Parametres" : act.setContentView(R.layout.liste_vins);
                    break;*/
                }
                if (intent != null) {
                    a.finish();
                    a.startActivity(intent);
                }
            }
        });
    }


}
