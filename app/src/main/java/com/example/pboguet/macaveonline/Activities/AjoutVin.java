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
import com.example.pboguet.macaveonline.Class.Mousseux;
import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.Class.VinBlanc;
import com.example.pboguet.macaveonline.Class.VinRose;
import com.example.pboguet.macaveonline.Class.VinRouge;
import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.Adapters.LieuAchatAdapter;
import com.example.pboguet.macaveonline.Utils.Adapters.LieuStockageAdapter;
import com.example.pboguet.macaveonline.Utils.Adapters.RegionAdapter;
import com.example.pboguet.macaveonline.Utils.GestionListes;

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
        setContentView(R.layout.ajout_vin);

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

        Vin vinInitial = (Vin) getIntent().getExtras().get("Vin");
        // Copie d'un vin
        if(vinInitial != null) {
            nom.setText(vinInitial.getNom());
            nbBouteilles.setText(Integer.toString(vinInitial.getNbBouteilles()));
            annee.setText(Integer.toString(vinInitial.getAnnee()));
            suivi.setChecked(vinInitial.isSuiviStock());
            favori.setChecked(vinInitial.isFavori());
            int reg = vinInitial.getRegion();
            region.setText(GestionListes.getNomRegion(reg));
            idRegion.setText(Integer.toString(reg));
            //appellation.setText(GestionListes);
            //idAppellation
            int type = vinInitial.getType();
            typeVin.setText(GestionListes.getNomType(type));
            idType.setText(Integer.toString(type));
            prix.setText(Float.toString(vinInitial.getPrixAchat()));
            degre.setText(Float.toString(vinInitial.getDegreAlcool()));
            offert.setText(vinInitial.getOffertPar());
            consoPartir.setText(vinInitial.getConsoPartir());
            int lieuA = vinInitial.getLieuAchat();
            lieuAchat.setText(GestionListes.getNomLieuAchat(lieuA));
            idLieuAchat.setText(Integer.toString(lieuA));
            consoAvant.setText(vinInitial.getConsoAvant());
            int lieuS = vinInitial.getLieuStockage();
            lieuStockage.setText(GestionListes.getNomLieuStockage(lieuS));
            idLieuStockage.setText(Integer.toString(lieuS));
            note.setRating(vinInitial.getNote()/4);
            commentaires.setText(vinInitial.getCommentaires());
        }


        // Traitement des clicks
        annee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeAnnee("annee", dialog);
            }
        });
        consoAvant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeAnnee("avant", dialog);
            }
        });
        consoPartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeAnnee("partir", dialog);
            }
        });
        region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listePopUp("region", dialog);
            }
        });
        /*appellation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listePopUp("appellation", dialog);
            }
        });*/
        typeVin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listePopUp("type", dialog);
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

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vin vin = new Vin();

                String deg = degre.getText().toString();
                String nb = nbBouteilles.getText().toString();
                String prixA = prix.getText().toString();
                int idT = Integer.parseInt(idType.getText().toString());

                vin.setNom(nom.getText().toString());
                vin.setAnnee(Integer.parseInt(annee.getText().toString()));
                vin.setRegion(Integer.parseInt(idRegion.getText().toString()));
                //appellation
                vin.setType(idT);
                if(deg.isEmpty() || deg.equals("0.0"))
                    vin.setDegreAlcool(0.0f);
                else
                    vin.setDegreAlcool(Float.parseFloat(deg));
                vin.setLieuStockage(Integer.parseInt(idLieuStockage.getText().toString()));
                vin.setLieuAchat(Integer.parseInt(idLieuAchat.getText().toString()));
                vin.setConsoPartir(consoPartir.getText().toString());
                vin.setConsoAvant(consoAvant.getText().toString());
                vin.setNote(note.getRating() * 4);
                if(nb.isEmpty())
                    vin.setNbBouteilles(0);
                else
                    vin.setNbBouteilles(Integer.parseInt((nb)));
                vin.setSuiviStock(suivi.isChecked());
                vin.setFavori(favori.isChecked());
                if(prixA.isEmpty() || prixA.equals("0.0"))
                    vin.setPrixAchat(0);
                else
                    vin.setPrixAchat(Float.parseFloat(prixA));
                vin.setOffertPar(offert.getText().toString());
                vin.setCommentaires(commentaires.getText().toString());
                //vin.setUtilisateur(LoginActivity.myUtilisateur.getUserId());
                vin.setUtilisateur(3);

                ControleurPrincipal.listeVins.add(vin);
                switch (idT) {
                    case 1 : ControleurPrincipal.listeVinsBlanc.add((VinBlanc) vin);
                        break;
                    case 2 : ControleurPrincipal.listeVinsRouge.add((VinRouge) vin);
                        break;
                    case 3 : ControleurPrincipal.listeVinsRose.add((VinRose) vin);
                        break;
                    case 4 : ControleurPrincipal.listeMousseux.add((Mousseux) vin);
                        break;
                }
                WebService.insertVin(vin);
            }
        });
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
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
        dialog.show();
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
