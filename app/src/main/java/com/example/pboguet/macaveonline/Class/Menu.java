package com.example.pboguet.macaveonline.Class;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
    private ImageView icone;

    public Menu(Context c, final Activity a, ListView l)
    {
        l = (ListView) l.findViewById(R.id.menu);
        final ArrayAdapter<String> menuAda = new ArrayAdapter<String>(c,R.layout.nom_menu, R.id.nomMenu,
                ControleurPrincipal.menu);
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
                        if (a != MyMainActivity.getInstance()) {
                            intent = new Intent(a, MyMainActivity.class);
                        }
                        break;
                    case "Recherche":
                        if (a != Recherche.getInstance()) {
                            intent = new Intent(a, Recherche.class);
                        }
                        break;
                    case "Ajouter":
                        if (a != AjoutVin.getInstance()) {
                            intent = new Intent(a, AjoutVin.class);
                        }
                        break;
                }
                // Ergonomie : pour éviter de fermer l'application si on annule dans le menu Ajouter
                // on ne termine pas l'activité précédente
                if (intent != null && !valMenu.equals("Ajouter")) {
                    a.finish();
                    a.startActivity(intent);
                } else if (intent != null && valMenu.equals("Ajouter")) {
                    a.startActivity(intent);
                }
            }
        });
    }

    /*private void setIcone()
    {
        for (int i = 0;i < ControleurPrincipal.menu.size();i++)
        {
            switch (ControleurPrincipal.menu.get(i))
            {
                case "Ma Cave" : {
                    icone.setImageResource(R.mipmap.ic_cave);
                }
                break;
                case "Recherche" : {
                    icone.setImageResource(R.mipmap.ic_search);
                }
                break;
                case "Ajouter" : {
                    icone.setImageResource(R.mipmap.ic_ajout);
                }
                break;
            }
        }
    }*/
}
