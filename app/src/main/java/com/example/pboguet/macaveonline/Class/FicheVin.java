package com.example.pboguet.macaveonline.Class;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pboguet.macaveonline.R;

import java.io.Serializable;


/**
 * Created by pboguet on 27/08/2015.
 */
public class FicheVin extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_vin);
        EditText nom = (EditText)findViewById(R.id.nomVin);
        Button moins = (Button) findViewById(R.id.nbMoins);
        EditText nbBouteilles = (EditText)findViewById(R.id.nbBouteilles);
        Button plus = (Button) findViewById(R.id.nbPlus);
        EditText annee = (EditText)findViewById(R.id.anneeVin);
        CheckBox suivi = (CheckBox)findViewById(R.id.suiviVin);
        EditText region = (EditText)findViewById(R.id.regionVin);
        CheckBox favori = (CheckBox)findViewById(R.id.favoriVin);
        EditText type = (EditText)findViewById(R.id.typeVin);
        EditText prix = (EditText)findViewById(R.id.prixVin);
        EditText degre = (EditText)findViewById(R.id.degre);
        EditText offert = (EditText)findViewById(R.id.offert);
        EditText consoPartir = (EditText)findViewById(R.id.consoPartirVin);
        EditText lieuAchat = (EditText)findViewById(R.id.lieuAchatVin);
        EditText consoAvant = (EditText)findViewById(R.id.consoAvantVin);
        EditText lieuStockage = (EditText)findViewById(R.id.lieuStockageVin);
        RatingBar note = (RatingBar) findViewById(R.id.noteVin);
        EditText commentaires = (EditText) findViewById(R.id.commentairesVin);
        Button modifier = (Button) findViewById(R.id.btnModifier);
        Button supprimer = (Button) findViewById(R.id.supprimerVin);
        Button copier = (Button) findViewById(R.id.copierVin);
        Button annuler = (Button) findViewById(R.id.annuler);
        ListView menu = (ListView) findViewById(R.id.menu);

        Serializable vin = getIntent().getSerializableExtra("Vin");
        vin.getClass();

    }
}
