package com.example.pboguet.macaveonline.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
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
public class FicheVin extends Activity
{
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
    private TextView consoPartirNum;
    private TextView lieuAchat;
    private TextView consoAvant;
    private TextView consoAvantNum;
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
    private static Typeface JELLYKA;
    private static Typeface MAIANDRA;
    private Context mContext;

    public static Activity getInstance() {
        return mActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mContext = getApplicationContext();
        setContentView(R.layout.detail_vin);
        JELLYKA = Typeface.createFromAsset(getAssets(), "fonts/JellykaWonderlandWine.ttf");
        MAIANDRA = Typeface.createFromAsset(getAssets(), "fonts/MaiandraGD.ttf");
        TextView titre = (TextView) findViewById(R.id.titreFiche);
        titre.setTypeface(JELLYKA);

        new Menu(mContext, this, (ListView) findViewById(R.id.menu));

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
        consoPartirNum = (TextView) findViewById(R.id.consoPartirNum);
        lieuAchat = (TextView)findViewById(R.id.lieuAchatVin);
        consoAvant = (TextView)findViewById(R.id.consoAvantVin);
        consoAvantNum = (TextView) findViewById(R.id.consoAvantNum);
        lieuStockage = (TextView)findViewById(R.id.lieuStockageVin);
        note = (RatingBar) findViewById(R.id.noteVin);
        noteSurVingt = (TextView) findViewById(R.id.noteSurVingt);
        commentaires = (EditText) findViewById(R.id.commentairesVin);
        modifier = (Button) findViewById(R.id.btnModifier);
        modifier.setTypeface(MAIANDRA);
        supprimer = (Button) findViewById(R.id.supprimerVin);
        supprimer.setTypeface(MAIANDRA);
        copier = (Button) findViewById(R.id.copierVin);
        copier.setTypeface(MAIANDRA);
        annuler = (Button) findViewById(R.id.annuler);
        annuler.setTypeface(MAIANDRA);
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
            appellation.setText(GestionListes.getNomAppellation(vin.getAppellation(), vin.getRegion()));
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
        if(!vin.getConsoPartir().equals(""))
        {
            String[] consoP = vin.getConsoPartir().split("-");
            consoPartir.setText(ControleurPrincipal.numeroMoisEnLettre(Integer.parseInt(consoP[1]), false) + " " + consoP[2]);
            consoPartirNum.setText(consoP[1]+"-"+consoP[2]);
        }
        else
        {
            consoPartir.setText("");
            consoPartirNum.setText("");
        }

        if(!vin.getConsoAvant().equals(""))
        {
            String[] consoA = vin.getConsoAvant().split("-");
            consoAvant.setText(ControleurPrincipal.numeroMoisEnLettre(Integer.parseInt(consoA[1]), false) + " " + consoA[2]);
            consoAvantNum.setText(consoA[1]+"-"+consoA[2]);
        }
        else
        {
            consoAvant.setText("");
            consoAvantNum.setText("");
        }

        if(vin.getLieuAchat() == 0) {
            lieuAchat.setText("");
        }
        else {
            lieuAchat.setText(GestionListes.getNomLieuAchat(vin.getLieuAchat()));
        }
        if(vin.getLieuStockage() == 0) {
            lieuStockage.setText("");
        }
        else {
            lieuStockage.setText(GestionListes.getNomLieuStockage(vin.getLieuStockage()));
        }

        if(vin.getNote() == 0.0f) {
            noteSurVingt.setText("");
        }
        else
        {
            note.setRating(vin.getNote() / 4);
            noteSurVingt.setText(Float.toString(vin.getNote()));
        }

        commentaires.setText(vin.getCommentaires());

        // Listeners de click sur les éléments de la vue
        moins.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int nb = Integer.parseInt(nbBouteilles.getText().toString());
                if (nb > 0) {
                    nbBouteilles.setText(Integer.toString(nb - 1));
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                nbBouteilles.setText(Integer.toString(Integer.parseInt(nbBouteilles.getText().toString()) + 1));
            }
        });

        note.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                noteSurVingt.setText(Float.toString(rating*4));
            }
        });

        consoPartir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                listeDateConso("partir", dialog);
            }
        });
        consoAvant.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                listeDateConso("avant", dialog);
            }
        });

        lieuAchat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                listePopUp("achat", dialog);
            }
        });

        lieuStockage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                listePopUp("stockage", dialog);
            }
        });

        supprimer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                WebService.deleteVin(vin);
            }
        });

        modifier.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Vin vinModifie = new Vin();

                String typeV = type.getText().toString();
                int idType = 0;
                switch(typeV)
                {
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
                if(consoPartirNum.getText().toString().equals("")) {
                    vinModifie.setConsoPartir("");
                }
                else {
                    vinModifie.setConsoPartir("01-" + consoPartirNum.getText().toString());
                }
                if(consoAvantNum.getText().toString().equals("")) {
                    vinModifie.setConsoAvant("");
                }
                else {
                    vinModifie.setConsoAvant("01-" + consoAvantNum.getText().toString());
                }

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

    /**
     * Création d'une liste de choix dans un dialog
     * @param type Type de contenu de la liste (Région, Appellation, etc...)
     * @param dialog Dialog où on affiche la liste
     */
    private void listePopUp(final String type, final Dialog dialog)
    {
        dialog.setContentView(R.layout.liste_choix_popup);
        ListView listeChoix = (ListView) dialog.findViewById(R.id.listeChoix);
        if(type.equals("achat")) {
            dialog.setTitle("Lieu d'achat");
            ArrayAdapter achatAda = new LieuAchatAdapter(mContext,R.layout.liste_choix_item, R.id.nom, ControleurPrincipal.listeLieuAchat);
            listeChoix.setAdapter(achatAda);
        }
        else {
            dialog.setTitle("Lieu de stockage");
            ArrayAdapter stockageAda = new LieuStockageAdapter(mContext,R.layout.liste_choix_item, R.id.nom, ControleurPrincipal.listeLieuStockage);
            listeChoix.setAdapter(stockageAda);
        }
        listeChoix.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if (type.equals("achat")) {
                    lieuAchat.setText(GestionListes.getNomLieuAchat(position));
                }
                else {
                    lieuStockage.setText(GestionListes.getNomLieuStockage(position));
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * Construction du calendrier pour chois dates de consommation
     * @param conso Type de date de consommation (Mini ou Maxi)
     * @param dialog Dialog pour affichage du calendrier
     */
    private void listeDateConso(final String conso, final Dialog dialog)
    {
        dialog.setContentView(R.layout.liste_annees);
        if(conso.equals("partir")) {
            dialog.setTitle("Date début consommation");
        }
        else {
            dialog.setTitle("Date consommation maximale");
        }
        Button valider = (Button) dialog.findViewById(R.id.validerDate);
        Button annuler = (Button) dialog.findViewById(R.id.annuler);
        final DatePicker pickerAnnee = (DatePicker) dialog.findViewById(R.id.datePicker);
        // on cache le jour ansi que le calendrier
        pickerAnnee.findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        pickerAnnee.setCalendarViewShown(false);

        valider.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String moisLettre = ControleurPrincipal.numeroMoisEnLettre(pickerAnnee.getMonth(), true);
                int mois = pickerAnnee.getMonth() + 1;
                String an = Integer.toString(pickerAnnee.getYear());
                if (conso.equals("partir")) {
                    consoPartir.setText(moisLettre + " " + an);
                    if(mois < 10) {
                        consoPartirNum.setText("0" + mois + "-" + an);
                    }
                    else {
                        consoPartirNum.setText(mois + "-" + an);
                    }
                }
                else {
                    consoAvant.setText(moisLettre + " " + an);
                    if(mois < 10) {
                        consoAvantNum.setText("0" + mois + "-" + an);
                    }
                    else {
                        consoAvantNum.setText(mois + "-" + an);
                    }
                }
                dialog.dismiss();
            }
        });
        annuler.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}


