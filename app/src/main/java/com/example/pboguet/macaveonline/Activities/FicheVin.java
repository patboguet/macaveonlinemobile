package com.example.pboguet.macaveonline.Activities;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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
import com.example.pboguet.macaveonline.Class.Menu;
import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.Adapters.LieuAchatAdapter;
import com.example.pboguet.macaveonline.Utils.Adapters.LieuStockageAdapter;
import com.example.pboguet.macaveonline.Utils.GestionListes;


/**
 * Created by pboguet on 27/08/2015.
 */
public class FicheVin extends Activity {

    private TextView id;
    private TextView nom;
    private Button moins;
    private EditText nbBouteilles;
    private Button plus;
    private TextView annee;
    private CheckBox suivi;
    private TextView region;
    private TextView appellation;
    private CheckBox favori;
    private TextView type;
    private EditText prix;
    private TextView degre;
    private EditText offert;
    private TextView consoPartir;
    private TextView lieuAchat;
    private TextView consoAvant;
    private TextView lieuStockage;
    private RatingBar note;
    private TextView noteSurVingt;
    private EditText commentaires;
    private Button modifier;
    private Button supprimer;
    private Button copier;
    private Button annuler;
    private ListView menu;
    private Dialog dialog;
    private static Activity mActivity;

    public static Activity getInstance() {
        return mActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
        setContentView(R.layout.detail_vin);
        new Menu(getApplicationContext(), this, (ListView) findViewById(R.id.menu));

        id = (TextView) findViewById(R.id.idVin);
        nom = (TextView)findViewById(R.id.nomVin);
        moins = (Button) findViewById(R.id.nbMoins);
        nbBouteilles = (EditText)findViewById(R.id.nbBouteilles);
        plus = (Button) findViewById(R.id.nbPlus);
        annee = (TextView)findViewById(R.id.anneeVin);
        suivi = (CheckBox)findViewById(R.id.suiviVin);
        region = (TextView)findViewById(R.id.regionVin);
        appellation = (TextView)findViewById(R.id.aocVin);
        favori = (CheckBox)findViewById(R.id.favoriVin);
        type = (TextView)findViewById(R.id.typeVin);
        prix = (EditText)findViewById(R.id.prixVin);
        degre = (TextView)findViewById(R.id.degre);
        offert = (EditText)findViewById(R.id.offert);
        consoPartir = (TextView)findViewById(R.id.consoPartirVin);
        lieuAchat = (TextView)findViewById(R.id.lieuAchatVin);
        consoAvant = (TextView)findViewById(R.id.consoAvantVin);
        lieuStockage = (TextView)findViewById(R.id.lieuStockageVin);
        note = (RatingBar) findViewById(R.id.noteVin);
        noteSurVingt = (TextView) findViewById(R.id.noteSurVingt);
        commentaires = (EditText) findViewById(R.id.commentairesVin);
        modifier = (Button) findViewById(R.id.btnModifier);
        supprimer = (Button) findViewById(R.id.supprimerVin);
        copier = (Button) findViewById(R.id.copierVin);
        annuler = (Button) findViewById(R.id.annuler);
        menu = (ListView) findViewById(R.id.menu);
        dialog = new Dialog(this);

        // Affectation des données du vin
        final Vin vin = (Vin) getIntent().getExtras().get("Vin");

        id.setText(Integer.toString(vin.getIdVin()));
        nom.setText(vin.getNom());
        nbBouteilles.setText(Integer.toString(vin.getNbBouteilles()));
        annee.setText(Integer.toString(vin.getAnnee()));
        suivi.setChecked(vin.isSuiviStock());
        int idR = vin.getRegion();
        region.setText(GestionListes.getNomRegion(idR));
        // pas d'appellation si Mousseux ou Vins Etrangers
        if(idR != 6 && idR != 12) {
            appellation.setText(GestionListes.getNomAppellation(vin.getAppellation(),vin.getRegion()));
        }
        favori.setChecked(vin.isFavori());
        type.setText(GestionListes.getNomType(vin.getType()));
        if(vin.getPrixAchat() > 0.0f) {
            prix.setText(Float.toString(vin.getPrixAchat()));
        }
        else prix.setText("");
        if(vin.getDegreAlcool() > 0.0f) {
            degre.setText(Float.toString(vin.getDegreAlcool()));
        }
        else degre.setText("");
        offert.setText(vin.getOffertPar());
        if(vin.getConsoPartir() != null) {
            String[] consoP = vin.getConsoPartir().split("-");
            consoPartir.setText(consoP[1]+"/"+consoP[2]);
        }
        else
            consoPartir.setText("");

        if(vin.getConsoAvant() != null) {
            String[] consoA = vin.getConsoAvant().split("-");
            consoAvant.setText(consoA[1]+"/"+consoA[2]);
        }
        else
            consoAvant.setText("");

        lieuAchat.setText(GestionListes.getNomLieuAchat(vin.getLieuAchat()));
        lieuStockage.setText(GestionListes.getNomLieuStockage(vin.getLieuStockage()));
        note.setRating(vin.getNote() / 4);
        noteSurVingt.setText(Float.toString(vin.getNote()));
        commentaires.setText(vin.getCommentaires());

        // Listeners de click sur les éléments de la vue
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

        note.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                noteSurVingt.setText(Float.toString(rating*4));
            }
        });

        consoPartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeDateConso("partir", dialog);
            }
        });
        consoAvant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeDateConso("avant", dialog);
            }
        });

        lieuAchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listePopUp("achat", dialog);
            }
        });

        lieuStockage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listePopUp("stockage", dialog);
            }
        });

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebService.deleteVin(vin);
            }
        });

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vin vinModifie = new Vin();

                String typeV = type.getText().toString();
                int idType = 0;
                switch(typeV) {
                    case "Blanc" : idType = 1;
                        break;
                    case "Rouge" : idType = 2;
                        break;
                    case "Rosé" : idType = 3;
                        break;
                    case "Mousseux" : idType = 4;
                        break;
                }
                vinModifie.setUtilisateur(vin.getUtilisateur());
                vinModifie.setIdVin(Integer.parseInt(id.getText().toString()));
                vinModifie.setType(idType);
                vinModifie.setLieuStockage(GestionListes.getIdLieuStockage(lieuStockage.getText().toString()));
                vinModifie.setLieuAchat(GestionListes.getIdLieuAchat(lieuAchat.getText().toString()));
                vinModifie.setConsoPartir("01/" + consoPartir.getText().toString());
                vinModifie.setConsoAvant("01/" + consoAvant.getText().toString());
                vinModifie.setNote(note.getRating() * 4);
                if(nbBouteilles.getText().toString().isEmpty()) {
                    vinModifie.setNbBouteilles(0);
                }
                else {
                    vinModifie.setNbBouteilles(Integer.parseInt((nbBouteilles.getText().toString())));
                }
                vinModifie.setSuiviStock(suivi.isChecked());
                vinModifie.setFavori(favori.isChecked());
                if(prix.getText().toString().equals("") || prix.getText().toString().equals("0.0f")) {
                    vinModifie.setPrixAchat(0.0f);
                }
                else {
                    vinModifie.setPrixAchat(Float.parseFloat(prix.getText().toString()));
                }
                vinModifie.setOffertPar(offert.getText().toString());
                vinModifie.setCommentaires(commentaires.getText().toString());
                WebService.updateVin(vinModifie);
            }
        });

        // COPIER VIN
        copier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AjoutVin.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Vin", vin);
                mActivity.finish();
                mActivity.startActivity(intent);
            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

    private void listePopUp(final String type, final Dialog dialog) {
        dialog.setContentView(R.layout.liste_choix_popup);
        ListView listeChoix = (ListView) dialog.findViewById(R.id.listeChoix);
        if(type.equals("achat")) {
                dialog.setTitle("Lieu d'achat");
                ArrayAdapter achatAda = new LieuAchatAdapter(getApplicationContext(),R.layout.liste_choix_item, R.id.nom, ControleurPrincipal.listeLieuAchat);
                listeChoix.setAdapter(achatAda);
            }
        else
        {
            dialog.setTitle("Lieu de stockage");
            ArrayAdapter stockageAda = new LieuStockageAdapter(getApplicationContext(),R.layout.liste_choix_item, R.id.nom, ControleurPrincipal.listeLieuStockage);
            listeChoix.setAdapter(stockageAda);
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

    private void listeDateConso(final String conso, final Dialog dialog) {
        dialog.setContentView(R.layout.liste_annees);
        dialog.setTitle("Date début consommation");
        Button valider = (Button) dialog.findViewById(R.id.validerDate);
        Button annuler = (Button) dialog.findViewById(R.id.annuler);
        final DatePicker listeAnnee = (DatePicker) dialog.findViewById(R.id.datePicker);
        // on cache le jour ansi que le calendrier
        listeAnnee.findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        listeAnnee.setCalendarViewShown(false);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (conso.equals("partir")) {
                    consoPartir.setText(Integer.toString(listeAnnee.getMonth() + 1) + "/" + Integer.toString(listeAnnee.getYear()));
                } else {
                    consoAvant.setText(Integer.toString(listeAnnee.getMonth() + 1) + "/" + Integer.toString(listeAnnee.getYear()));
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
        dialog.show();
    }
}


