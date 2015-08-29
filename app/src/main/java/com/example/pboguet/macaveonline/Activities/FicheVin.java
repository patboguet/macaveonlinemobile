package com.example.pboguet.macaveonline.Activities;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.ControleurPrincipal;
import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.GestionListes;
import com.example.pboguet.macaveonline.Utils.Adapters.RegionAdapter;


/**
 * Created by pboguet on 27/08/2015.
 */
public class FicheVin extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_vin);
        EditText nom = (EditText)findViewById(R.id.nomVin);
        Button moins = (Button) findViewById(R.id.nbMoins);
        final EditText nbBouteilles = (EditText)findViewById(R.id.nbBouteilles);
        Button plus = (Button) findViewById(R.id.nbPlus);
        final TextView annee = (TextView)findViewById(R.id.anneeVin);
        CheckBox suivi = (CheckBox)findViewById(R.id.suiviVin);
        final TextView region = (TextView)findViewById(R.id.regionVin);
        final TextView appellation = (TextView)findViewById(R.id.aocVin);
        CheckBox favori = (CheckBox)findViewById(R.id.favoriVin);
        final TextView type = (TextView)findViewById(R.id.typeVin);
        EditText prix = (EditText)findViewById(R.id.prixVin);
        EditText degre = (EditText)findViewById(R.id.degre);
        EditText offert = (EditText)findViewById(R.id.offert);
        final TextView consoPartir = (TextView)findViewById(R.id.consoPartirVin);
        final TextView lieuAchat = (TextView)findViewById(R.id.lieuAchatVin);
        final TextView consoAvant = (TextView)findViewById(R.id.consoAvantVin);
        final TextView lieuStockage = (TextView)findViewById(R.id.lieuStockageVin);
        RatingBar note = (RatingBar) findViewById(R.id.noteVin);
        EditText commentaires = (EditText) findViewById(R.id.commentairesVin);
        Button modifier = (Button) findViewById(R.id.btnModifier);
        Button supprimer = (Button) findViewById(R.id.supprimerVin);
        Button copier = (Button) findViewById(R.id.copierVin);
        Button annuler = (Button) findViewById(R.id.annuler);
        ListView menu = (ListView) findViewById(R.id.menu);
        final Dialog dialog = new Dialog(this);

        Vin vin = (Vin) getIntent().getExtras().get("Vin");
        nom.setText(vin.getNom());
        nbBouteilles.setText(Integer.toString(vin.getNbBouteilles()));
        annee.setText(Integer.toString(vin.getAnnee()));
        suivi.setChecked(vin.isSuiviStock());
        region.setText(GestionListes.getNomRegion(vin.getRegion()));
        favori.setChecked(vin.isFavori());
        type.setText(GestionListes.getNomType(vin.getType()));
        if(vin.getPrixAchat() > 0.0f) {
            prix.setText(Float.toString(vin.getPrixAchat()));
        }
        else prix.setText("");
        if(vin.getDegreAlcool() > 0.0f) {
            degre.setText(Float.toString(vin.getDegreAlcool()));}
        else degre.setText("");
        offert.setText(vin.getOffertPar());
        consoPartir.setText((CharSequence) vin.getConsoPartir());
        lieuAchat.setText(GestionListes.getNomLieuAchat(vin.getLieuAchat()));
        consoAvant.setText((CharSequence) vin.getConsoAvant());
        lieuStockage.setText(GestionListes.getNomLieuStockage(vin.getLieuStockage()));
        note.setRating(vin.getNote());
        commentaires.setText(vin.getCommentaires());

        moins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nb = Integer.parseInt(nbBouteilles.getText().toString());
                if (nb > 0) {
                    nbBouteilles.setText(Integer.toString(nb - 1));
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbBouteilles.setText(Integer.toString(Integer.parseInt(nbBouteilles.getText().toString()) + 1));
            }
        });
        region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.liste_choix_popup);
                dialog.setTitle("Choix Region");
                ListView listeRegion = (ListView) dialog.findViewById(R.id.listeChoix);
                RegionAdapter regionAda = new RegionAdapter(getApplicationContext(), R.layout.liste_regions, ControleurPrincipal.listeRegion);
                listeRegion.setAdapter(regionAda);
                listeRegion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        region.setText(GestionListes.getNomRegion(position + 1));
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        //TODO Faire listener click sur TextView

    }
}
