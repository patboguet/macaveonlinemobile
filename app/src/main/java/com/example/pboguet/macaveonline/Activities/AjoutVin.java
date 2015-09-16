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

import com.example.pboguet.macaveonline.Class.Appellation;
import com.example.pboguet.macaveonline.Class.ControleurPrincipal;
import com.example.pboguet.macaveonline.Class.Menu;
import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.Adapters.AppellationAdapter;
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
    private Button moins;
    private Button plus;
    private TextView annee;
    private CheckBox suivi;
    private CheckBox favori;
    private TextView region;
    private TextView idRegion;
    private TextView labelAppellation;
    private TextView appellation;
    private TextView idAppellation;
    private TextView type;
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
    private static Activity mActivity;
    private ArrayList listeType = new ArrayList(5);
    private TextView erreurNom;
    private TextView erreurRegion;
    private TextView erreurAnnee;
    private TextView erreurType;
    private TextView noteSurVingt;

    public static Activity getInstance() {
        return mActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.ajout_vin);
        new Menu(getApplicationContext(), this, (ListView) findViewById(R.id.menu));

        listeType.add(0, "Type de vin");
        listeType.add(1, "Blanc");
        listeType.add(2, "Rouge");
        listeType.add(3, "Rosé");
        listeType.add(4, "Mousseux");

        nom = (EditText)findViewById(R.id.nomVin);
        nbBouteilles = (EditText)findViewById(R.id.nbBouteilles);
        moins = (Button) findViewById(R.id.nbMoins);
        plus = (Button) findViewById(R.id.nbPlus);
        annee = (TextView)findViewById(R.id.anneeVin);
        suivi = (CheckBox)findViewById(R.id.suiviVin);
        region = (TextView)findViewById(R.id.regionVin);
        idRegion = (TextView)findViewById(R.id.idRegion);
        appellation = (TextView)findViewById(R.id.appellation);
        idAppellation = (TextView) findViewById(R.id.idAoc);
        favori = (CheckBox)findViewById(R.id.favoriVin);
        type = (TextView)findViewById(R.id.typeVin);
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
        noteSurVingt = (TextView) findViewById(R.id.noteSurVingt);
        commentaires = (EditText) findViewById(R.id.commentairesVin);
        ajouter = (Button) findViewById(R.id.ajouterVin);
        annuler = (Button) findViewById(R.id.annuler);
        menu = (ListView) findViewById(R.id.menu);
        dialog = new Dialog(this);
        erreurNom = (TextView) findViewById(R.id.erreurNom);
        erreurAnnee = (TextView) findViewById(R.id.erreurAnnee);
        erreurRegion = (TextView) findViewById(R.id.erreurRegion);
        erreurType = (TextView) findViewById(R.id.erreurType);

        // Copie d'un vin
        if(getIntent().hasExtra("Vin")) {
            Vin vinInitial = (Vin) getIntent().getExtras().get("Vin");
            nom.setText(vinInitial.getNom());
            nbBouteilles.setText(Integer.toString(vinInitial.getNbBouteilles()));
            annee.setText(Integer.toString(vinInitial.getAnnee()));
            suivi.setChecked(vinInitial.isSuiviStock());
            favori.setChecked(vinInitial.isFavori());
            int reg = vinInitial.getRegion();
            region.setText(GestionListes.getNomRegion(reg));
            idRegion.setText(Integer.toString(reg));
            int aoc = vinInitial.getAppellation();
            if(reg != 6 && reg != 12)
            {
                appellation.setText(GestionListes.getNomAppellation(aoc,reg));
                idAppellation.setText(Integer.toString(aoc));
            }
            else
            {
                labelAppellation.setVisibility(View.GONE);
                appellation.setVisibility(View.GONE);
            }
            int typeV = vinInitial.getType();
            type.setText(GestionListes.getNomType(typeV));
            idType.setText(Integer.toString(typeV));
            if(vinInitial.getPrixAchat() > 0.0f) {
                prix.setText(Float.toString(vinInitial.getPrixAchat()));
            }
            else prix.setText("");
            if(vinInitial.getDegreAlcool() > 0.0f) {
                degre.setText(Float.toString(vinInitial.getDegreAlcool()));
            }
            else degre.setText("");
            offert.setText(vinInitial.getOffertPar());
            if(vinInitial.getConsoPartir() != null) {
                String[] consoP = vinInitial.getConsoPartir().split("/");
                consoPartir.setText(consoP[1]+"/"+consoP[2]);
            }
            else
                consoPartir.setText("");

            if(vinInitial.getConsoAvant() != null) {
                String[] consoA = vinInitial.getConsoAvant().split("/");
                consoAvant.setText(consoA[1]+"/"+consoA[2]);
            }
            else
                consoAvant.setText("");
            int lieuA = vinInitial.getLieuAchat();
            lieuAchat.setText(GestionListes.getNomLieuAchat(lieuA));
            idLieuAchat.setText(Integer.toString(lieuA));
            int lieuS = vinInitial.getLieuStockage();
            lieuStockage.setText(GestionListes.getNomLieuStockage(lieuS));
            idLieuStockage.setText(Integer.toString(lieuS));
            note.setRating(vinInitial.getNote() / 4);
            noteSurVingt.setText(Float.toString(vinInitial.getNote()));
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
        appellation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    listePopUp("appellation", dialog);
            }
        });
        type.setOnClickListener(new View.OnClickListener() {
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
                noteSurVingt.setText(Float.toString(rating * 4));
            }
        });

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean erreur = verifChamps();

                if(!erreur) {
                    Vin vin = new Vin();

                    vin.setNom(nom.getText().toString());
                    vin.setAnnee(Integer.parseInt(annee.getText().toString()));
                    vin.setRegion(Integer.parseInt(idRegion.getText().toString()));

                    int idT = Integer.parseInt(idType.getText().toString());
                    vin.setType(idT);
                    String deg = degre.getText().toString();
                    if(deg.equals("") || deg.equals("0.0")) {
                        vin.setDegreAlcool(0.0f);
                    }
                    else {
                        vin.setDegreAlcool(Float.parseFloat(deg));
                    }
                    String idLS = idLieuStockage.getText().toString();
                    if(idLS.equals(""))
                    {
                        vin.setLieuStockage(0);
                    }
                    else {
                        vin.setLieuStockage(Integer.parseInt(idLS));
                    }
                    String idLA = idLieuAchat.getText().toString();
                    if(idLA.equals(""))
                    {
                        vin.setLieuAchat(0);
                    }
                    else {
                        vin.setLieuStockage(Integer.parseInt(idLA));
                    }
                    vin.setConsoPartir("01/"+consoPartir.getText().toString());
                    vin.setConsoAvant("01/"+consoAvant.getText().toString());
                    vin.setNote(note.getRating() * 4);
                    String nb = nbBouteilles.getText().toString();
                    if(nb.equals("")) {
                        vin.setNbBouteilles(0);
                    }
                    else {
                        vin.setNbBouteilles(Integer.parseInt((nb)));
                    }
                    vin.setSuiviStock(suivi.isChecked());
                    vin.setFavori(favori.isChecked());
                    String prixA = prix.getText().toString();
                    if(prixA.equals("") || prixA.equals("0.0")) {
                        vin.setPrixAchat(0);
                    }
                    else
                        vin.setPrixAchat(Float.parseFloat(prixA));
                    vin.setOffertPar(offert.getText().toString());
                    vin.setCommentaires(commentaires.getText().toString());
                    vin.setUtilisateur(LoginActivity.myUtilisateur.getUserId());

                    WebService.insertVin(vin);
                }
            }
        });
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

    private boolean verifChamps() {
        boolean erreur = false;

        //champs obligatoires
        String nomVin = nom.getText().toString();
        String anneeVin = annee.getText().toString();
        String regionVin = region.getText().toString();
        String typeVin = type.getText().toString();
        String base = "Veuillez renseigner ";
        if(nomVin.equals("")) {
            erreurNom.setText(base+"un nom.");
            erreur = true;
        }
        else {
            erreurNom.setVisibility(View.GONE);
        }
        if(anneeVin.equals("")) {
            erreurAnnee.setText(base+"une année.");
            erreur = true;
        }
        else {
            erreurAnnee.setVisibility(View.GONE);
        }
        if(regionVin.equals("")) {
            erreurRegion.setText(base+"une région.");
            erreur = true;
        }
        else {
            erreurRegion.setVisibility(View.GONE);
        }
        if(typeVin.equals("")) {
            erreurType.setText(base+"un type.");
            erreur = true;
        }
        else {
            erreurType.setVisibility(View.GONE);
        }
        return erreur;

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

    private void listePopUp(final String choix, final Dialog dialog) {
        dialog.setContentView(R.layout.liste_choix_popup);
        ListView listeChoix = (ListView) dialog.findViewById(R.id.listeChoix);
        switch (choix) {
            case "region": {
                dialog.setTitle("Région");
                RegionAdapter regionAda = new RegionAdapter(getApplicationContext(), R.layout.liste_choix_item, 0, ControleurPrincipal.listeRegion);
                listeChoix.setAdapter(regionAda);
            }
            break;
            case "appellation": {
                Integer idR = Integer.parseInt(idRegion.getText().toString());
                dialog.setTitle("Appellation");
                if (!idR.equals("")) {
                    ArrayList<Appellation> listeAppellations = ControleurPrincipal.listeRegionAoc.get(idR);
                    AppellationAdapter aocAda = new AppellationAdapter(getApplicationContext(), R.layout.liste_choix_item, R.id.nom, listeAppellations);
                    listeChoix.setAdapter(aocAda);
                }
            }
            break;
            case "achat": {
                dialog.setTitle("Lieu d'achat");
                LieuAchatAdapter achatAda = new LieuAchatAdapter(getApplicationContext(), R.layout.liste_choix_item, R.id.nom, ControleurPrincipal.listeLieuAchat);
                listeChoix.setAdapter(achatAda);
            }
            break;
            case "stockage": {
                dialog.setTitle("Lieu de stockage");
                LieuStockageAdapter stockageAda = new LieuStockageAdapter(getApplicationContext(), R.layout.liste_choix_item, R.id.nom, ControleurPrincipal.listeLieuStockage);
                listeChoix.setAdapter(stockageAda);
            }
            break;
            case "type": {
                dialog.setTitle("Type de vin");
                ArrayAdapter typeAda = new ArrayAdapter<>(getApplicationContext(), R.layout.liste_types, R.id.nomType, listeType);
                listeChoix.setAdapter(typeAda);
            }
        }

            listeChoix.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (choix) {
                        case "region": {
                            if(position>0){
                                region.setText(GestionListes.getNomRegion(position));
                            }
                            else {
                                region.setText("");
                            }
                            idRegion.setText(Integer.toString(position));
                        }
                        break;
                        case "appellation": {
                            int idR = Integer.parseInt(idRegion.getText().toString());
                            if(position>0 && idR > 0){
                                appellation.setText(GestionListes.getNomAppellation(position,idR));
                            }
                            else {
                                appellation.setText("");
                            }
                            idAppellation.setText(Integer.toString(position));
                        }
                        break;
                        case "achat": {
                            if(position>0){
                                lieuAchat.setText(GestionListes.getNomLieuAchat(position));
                            }
                            else {
                                lieuAchat.setText("");
                            }
                            idLieuAchat.setText(Integer.toString(position));
                        }
                        break;
                        case "stockage": {
                            if(position>0) {
                                lieuStockage.setText(GestionListes.getNomLieuStockage(position));
                            }
                            else {
                                lieuStockage.setText("");
                            }
                            idLieuStockage.setText(Integer.toString(position));
                        }
                        break;
                        case "type": {
                            if(position>0) {
                                type.setText(listeType.get(position).toString());
                            }
                            else {
                                type.setText("");
                            }
                            idType.setText(Integer.toString(position));
                        }
                        break;
                    }
                    dialog.dismiss();
                }
            });
        dialog.show();
    }
}
