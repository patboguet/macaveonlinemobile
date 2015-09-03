package com.example.pboguet.macaveonline.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.ControleurPrincipal;
import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.Adapters.LieuAchatAdapter;
import com.example.pboguet.macaveonline.Utils.Adapters.LieuStockageAdapter;
import com.example.pboguet.macaveonline.Utils.Adapters.RegionAdapter;
import com.example.pboguet.macaveonline.Utils.GestionListes;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by pboguet on 03/09/2015.
 */
public class AjoutVin extends Activity {

    private EditText nom;
    private EditText nbBouteilles;
    private TextView annee;
    private CheckBox suivi;
    private CheckBox favori;
    private TextView region;
    private TextView idRegion;
    private TextView appellation;
    private TextView idAppellation;
    private TextView typeVin;
    private TextView idType;
    private EditText prix;
    private EditText degre;
    private EditText offert;
    private TextView consoPartir;
    private TextView lieuAchat;
    private TextView idLieuAchat;
    private TextView consoAvant;
    private TextView lieuStockage;
    private TextView idLieuStockage;
    private RatingBar note;
    private EditText commentaires;
    private Button ajouter;
    private Button annuler;
    private ListView menu;
    private Dialog dialog;
    private Activity mActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
        nom = (EditText)findViewById(R.id.nomVin);
        nbBouteilles = (EditText)findViewById(R.id.nbBouteilles);
        annee = (TextView)findViewById(R.id.anneeVin);
        suivi = (CheckBox)findViewById(R.id.suiviVin);
        region = (TextView)findViewById(R.id.regionVin);
        idRegion = (TextView)findViewById(R.id.idRegion);
        appellation = (TextView)findViewById(R.id.aocVin);
        idAppellation = (TextView) findViewById(R.id.idAoc);
        favori = (CheckBox)findViewById(R.id.favoriVin);
        typeVin = (TextView)findViewById(R.id.typeVin);
        idType = (TextView) findViewById(R.id.idType);
        prix = (EditText)findViewById(R.id.prixVin);
        degre = (EditText) findViewById(R.id.degre);
        offert = (EditText)findViewById(R.id.offert);
        consoPartir = (TextView)findViewById(R.id.consoPartirVin);
        lieuAchat = (TextView)findViewById(R.id.lieuAchatVin);
        idLieuAchat = (TextView) findViewById(R.id.idLieuAchat);
        consoAvant = (TextView)findViewById(R.id.consoAvantVin);
        lieuStockage = (TextView)findViewById(R.id.lieuStockageVin);
        idLieuStockage = (TextView) findViewById(R.id.idLieuStockage);
        note = (RatingBar) findViewById(R.id.noteVin);
        commentaires = (EditText) findViewById(R.id.commentairesVin);
        ajouter = (Button) findViewById(R.id.ajouterVin);
        annuler = (Button) findViewById(R.id.annuler);
        menu = (ListView) findViewById(R.id.menu);
        dialog = new Dialog(this);

        // Traitement des clicks
        annee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeAnnee("annee", dialog);
            }
        });
    }

    private void listeAnnee(final String liste, final Dialog dialog) {
        dialog.setContentView(R.layout.liste_annees);
        Button valider = (Button) dialog.findViewById(R.id.validerDate);
        Button annuler = (Button) dialog.findViewById(R.id.annuler);
        final DatePicker listeAnnee = (DatePicker) dialog.findViewById(R.id.datePicker);
        switch(liste)
        {
            case "annee" : {
                dialog.setTitle("Année du vin");
                listeAnnee.findViewById(Resources.getSystem().getIdentifier("month", "id", "android")).setVisibility(View.GONE);
            }
                break;
            case "partir" : {
                dialog.setTitle("Date début consommation");
            }
                break;
            case "avant" : {
                dialog.setTitle("Date max consommation");
            }
                break;
        }
        listeAnnee.findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        listeAnnee.setCalendarViewShown(false);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (liste) {
                    case "annee":
                        annee.setText(Integer.toString(listeAnnee.getYear()));
                        break;
                    case "partir":
                        consoPartir.setText("01/" + Integer.toString(listeAnnee.getMonth() + 1) + "/" + Integer.toString(listeAnnee.getYear()));
                        break;
                    case "avant":
                        consoAvant.setText("01/" + Integer.toString(listeAnnee.getMonth() + 1) + "/" + Integer.toString(listeAnnee.getYear()));
                        break;
                }
                dialog.dismiss();
            }
        });
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void listePopUp(final String type, final Dialog dialog) {
        dialog.setContentView(R.layout.liste_choix_popup);
        ListView listeChoix = (ListView) dialog.findViewById(R.id.listeChoix);
        switch (type)
        {
            case "region" : {
                dialog.setTitle("Région");
                ArrayAdapter regionAda = new RegionAdapter(getApplicationContext(),R.layout.liste_choix_item, ControleurPrincipal.listeRegion);
                listeChoix.setAdapter(regionAda);
            }
            break;
            case "appellation" : {
                dialog.setTitle("Appellation");
                //ArrayAdapter aocAda = new AocAdapter(getApplicationContext(),R.layout.liste_choix_item, ControleurPrincipal.listeAppellation);
                //listeChoix.setAdapter(aocAda);
            }
            break;
            case "achat" : {
                dialog.setTitle("Lieu d'achat");
                ArrayAdapter achatAda = new LieuAchatAdapter(getApplicationContext(),R.layout.liste_choix_item, ControleurPrincipal.listeLieuAchat);
                listeChoix.setAdapter(achatAda);
            }
            break;
            case "stockage" : {
                dialog.setTitle("Lieu de stockage");
                ArrayAdapter stockageAda = new LieuStockageAdapter(getApplicationContext(),R.layout.liste_choix_item,ControleurPrincipal.listeLieuStockage);
                listeChoix.setAdapter(stockageAda);
            }
            break;
            case "type" : {
                dialog.setTitle("Type de vin");
                final ArrayList listeType = new ArrayList(4);
                listeType.add(0,"Blanc");
                listeType.add(1, "Rouge");
                listeType.add(2, "Rosé");
                listeType.add(3, "Mousseux");
                ArrayAdapter typeAda = new ArrayAdapter<>(getApplicationContext(),R.layout.liste_types,R.id.nomType, listeType);
                listeChoix.setAdapter(typeAda);
            }
            listeChoix.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (type)
                    {
                        case "region" :{
                            region.setText(GestionListes.getNomLieuAchat(position + 1));
                            idRegion.setText(Integer.toString(position+1));
                        }
                            break;
                        case "appellation" :{
                            appellation.setText(GestionListes.getNomLieuAchat(position + 1));
                            idAppellation.setText(Integer.toString(position+1));
                        }
                            break;
                        case "achat" :{
                            lieuAchat.setText(GestionListes.getNomLieuAchat(position + 1));
                            idLieuAchat.setText(Integer.toString(position+1));
                        }
                            break;
                        case "stockage" :{
                            lieuStockage.setText(GestionListes.getNomLieuAchat(position + 1));
                            idLieuStockage.setText(Integer.toString(position+1));
                        }
                            break;
                        case "type" :{
                            typeVin.setText(GestionListes.getNomLieuAchat(position + 1));
                            idType.setText(Integer.toString(position+1));
                        }
                            break;
                    }
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

        listeChoix.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (type.equals("achat")) {
                    lieuAchat.setText(GestionListes.getNomLieuAchat(position + 1));
                } else {
                    lieuStockage.setText(GestionListes.getNomLieuStockage(position + 1));
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
