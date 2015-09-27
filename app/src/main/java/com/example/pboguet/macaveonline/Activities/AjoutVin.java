package com.example.pboguet.macaveonline.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
public class AjoutVin extends Activity
{

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
    private TextView consoPartirNum;
    private TextView lieuAchat;
    private TextView idLieuAchat;
    private TextView consoAvant;
    private TextView consoAvantNum;
    private TextView lieuStockage;
    private TextView idLieuStockage;
    private RatingBar note;
    private EditText commentaires;
    private Button ajouter;
    private Button annuler;
    private ListView menu;
    private Dialog dialog;
    private static Activity mActivity;
    private ArrayList listeType = new ArrayList(5);         // Tableau de la liste des types de vin
    private TextView erreurNom;
    private TextView erreurRegion;
    private TextView erreurAnnee;
    private TextView erreurType;
    private TextView noteSurVingt;
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
        setContentView(R.layout.ajout_vin);
        // Polices personnalisées
        JELLYKA = Typeface.createFromAsset(mContext.getAssets(), "fonts/JellykaWonderlandWine.ttf");
        MAIANDRA = Typeface.createFromAsset(mContext.getAssets(), "fonts/MaiandraGD.ttf");

        TextView titre = (TextView) findViewById(R.id.titreBarreHaut);
        titre.setTypeface(JELLYKA);

        // On crée le menu
        new Menu(mContext, this, (ListView) findViewById(R.id.menu));

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
        labelAppellation = (TextView) findViewById(R.id.labAoc);
        appellation = (TextView)findViewById(R.id.appellation);
        idAppellation = (TextView) findViewById(R.id.idAoc);
        favori = (CheckBox)findViewById(R.id.favoriVin);
        type = (TextView)findViewById(R.id.typeVin);
        idType = (TextView) findViewById(R.id.idType);
        prix = (EditText)findViewById(R.id.prixVin);
        degre = (EditText) findViewById(R.id.degre);
        offert = (EditText)findViewById(R.id.offert);
        consoPartir = (TextView)findViewById(R.id.consoPartirVin);
        consoPartirNum = (TextView) findViewById(R.id.consoPartirNum);
        lieuAchat = (TextView)findViewById(R.id.lieuAchatVin);
        idLieuAchat = (TextView) findViewById(R.id.idLieuAchat);
        consoAvant = (TextView)findViewById(R.id.consoAvantVin);
        consoAvantNum = (TextView) findViewById(R.id.consoAvantNum);
        lieuStockage = (TextView)findViewById(R.id.lieuStockageVin);
        idLieuStockage = (TextView) findViewById(R.id.idLieuStockage);
        note = (RatingBar) findViewById(R.id.noteVin);
        noteSurVingt = (TextView) findViewById(R.id.noteSurVingt);
        commentaires = (EditText) findViewById(R.id.commentairesVin);
        ajouter = (Button) findViewById(R.id.ajouterVin);
        ajouter.setTypeface(MAIANDRA);
        annuler = (Button) findViewById(R.id.annuler);
        annuler.setTypeface(MAIANDRA);
        menu = (ListView) findViewById(R.id.menu);
        dialog = new Dialog(this);
        erreurNom = (TextView) findViewById(R.id.erreurNom);
        erreurAnnee = (TextView) findViewById(R.id.erreurAnnee);
        erreurRegion = (TextView) findViewById(R.id.erreurRegion);
        erreurType = (TextView) findViewById(R.id.erreurType);

        // Copie d'un vin
        if(getIntent().hasExtra("Vin"))
        {
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
            // IdRegion 6 = Champagne et IdRegion 12 = Vins Etrangers donc pas d'appellations liées
            if(reg != 6 && reg != 12)
            {
                appellation.setText(GestionListes.getNomAppellation(aoc,reg));
                idAppellation.setText(Integer.toString(aoc));
            }
            else
            {
                labelAppellation.setVisibility(View.INVISIBLE);
                appellation.setVisibility(View.INVISIBLE);
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
            if(!vinInitial.getConsoPartir().equals(""))
            {
                String[] consoP = vinInitial.getConsoPartir().split("-");
                consoPartir.setText(ControleurPrincipal.numeroMoisEnLettre(Integer.parseInt(consoP[1]), false) + " " + consoP[2]);
                consoPartirNum.setText(consoP[1] + "-" +consoP[2]);
            }
            else
            {
                consoPartir.setText("");
                consoPartirNum.setText("");
            }
            if(!vinInitial.getConsoAvant().equals(""))
            {
                String[] consoA = vinInitial.getConsoAvant().split("-");
                consoAvant.setText(ControleurPrincipal.numeroMoisEnLettre(Integer.parseInt(consoA[1]), false) + " " + consoA[2]);
                consoAvantNum.setText(consoA[1] + "-" + consoA[2]);
            }
            else
            {
                consoAvant.setText("");
                consoAvantNum.setText("");
            }
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

        // Listeners des clics pour le renseignement des données
        annee.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                listeAnnee("annee", dialog);
            }
        });
        consoAvant.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                listeAnnee("avant", dialog);
            }
        });
        consoPartir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                listeAnnee("partir", dialog);
            }
        });
        region.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                listePopUp("region", dialog);
            }
        });
        appellation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                    listePopUp("appellation", dialog);
            }
        });
        type.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                listePopUp("type", dialog);
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
        moins.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String nb = nbBouteilles.getText().toString();
                int nbBt = 0;
                if (!nb.equals("")) {
                    nbBt = Integer.parseInt(nb);
                }
                if(nbBt > 0) {
                    nbBouteilles.setText(Integer.toString(nbBt - 1));
                }
                else {
                    nbBouteilles.setText("0");
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String nb = nbBouteilles.getText().toString();
                if(!nb.equals("")) {
                    nbBouteilles.setText(Integer.toString(Integer.parseInt(nbBouteilles.getText().toString()) + 1));
                }
                else {
                    nbBouteilles.setText("1");
                }
            }
        });
        note.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener()
        {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // Affichage de la note sur 20
                noteSurVingt.setText(Float.toString(rating * 4));
            }
        });

        // Insertion du vin dans la BDD
        ajouter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                boolean erreur = verifChampsObligatoires();

                if(!erreur)
                {
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
                    if(idLS.equals("")) {
                        vin.setLieuStockage(0);
                    }
                    else {
                        vin.setLieuStockage(Integer.parseInt(idLS));
                    }
                    String idLA = idLieuAchat.getText().toString();
                    if(idLA.equals("")) {
                        vin.setLieuAchat(0);
                    }
                    else {
                        vin.setLieuStockage(Integer.parseInt(idLA));
                    }
                    if(!consoPartirNum.getText().toString().equals("")) {
                        vin.setConsoPartir("01-" + consoPartirNum.getText().toString());
                    }
                    else {
                        vin.setConsoPartir("");
                    }
                    if(!consoAvantNum.getText().toString().equals("")) {
                        vin.setConsoAvant("01-"+consoAvantNum.getText().toString());
                    }
                    else {
                        vin.setConsoAvant("");
                    }
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
                    else {
                        vin.setPrixAchat(Float.parseFloat(prixA));
                    }
                    vin.setOffertPar(offert.getText().toString());
                    vin.setCommentaires(commentaires.getText().toString());
                    vin.setUtilisateur(LoginActivity.myUtilisateur.getUserId());

                    WebService.insertVin(vin);
                }
            }
        });
        annuler.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

    /**
     * Vérification des champs obligatoires pour insérer un vin
     * @return boolean Erreur sur un champ manquant
     */
    private boolean verifChampsObligatoires() {
        boolean erreur = false;

        //champs obligatoires
        String nomVin = nom.getText().toString();
        String anneeVin = annee.getText().toString();
        String regionVin = region.getText().toString();
        String typeVin = type.getText().toString();
        String baseMessage = "Veuillez renseigner ";
        if(nomVin.equals(""))
        {
            erreurNom.setText(baseMessage+"un nom.");
            erreur = true;
        }
        else {
            erreurNom.setVisibility(View.GONE);
        }
        if(anneeVin.equals(""))
        {
            erreurAnnee.setText(baseMessage+"une année.");
            erreur = true;
        }
        else {
            erreurAnnee.setVisibility(View.GONE);
        }
        if(regionVin.equals(""))
        {
            erreurRegion.setText(baseMessage+"une région.");
            erreur = true;
        }
        else {
            erreurRegion.setVisibility(View.GONE);
        }
        if(typeVin.equals(""))
        {
            erreurType.setText(baseMessage+"un type.");
            erreur = true;
        }
        else {
            erreurType.setVisibility(View.GONE);
        }
        return erreur;
    }

    /**
     * Création de la liste des dates quand il faut sélectionner une année sur la fiche
     * @param liste Type de date (Consommation ou Année du vin)
     * @param dialog Dialog où s'affiche la liste
     */
    private void listeAnnee(final String liste, final Dialog dialog)
    {
        dialog.setContentView(R.layout.liste_annees);
        Button valider = (Button) dialog.findViewById(R.id.validerDate);
        Button annuler = (Button) dialog.findViewById(R.id.annuler);
        final DatePicker pickerAnnee = (DatePicker) dialog.findViewById(R.id.datePicker);
        switch(liste)
        {
            case "annee" :
            {
                dialog.setTitle("Année du vin");
                // On cache le mois du calendrier
                pickerAnnee.findViewById(Resources.getSystem().getIdentifier("month", "id", "android")).setVisibility(View.GONE);
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
        // on cache le jour du calendrier
        pickerAnnee.findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        pickerAnnee.setCalendarViewShown(false);

        valider.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String moisLettre = ControleurPrincipal.numeroMoisEnLettre(pickerAnnee.getMonth(), true);
                int mois = pickerAnnee.getMonth() + 1;
                String an = Integer.toString(pickerAnnee.getYear());
                switch (liste)
                {
                    case "annee":
                        annee.setText(an);
                        break;
                    case "partir": {
                        consoPartir.setText(moisLettre + " " + an);
                        if(mois < 10) {
                            consoPartirNum.setText("0" + mois + "-" + an);
                        }
                        else {
                            consoPartirNum.setText(mois + "-" + an);
                        }
                    }
                        break;
                    case "avant": {
                        consoAvant.setText(moisLettre + " " + an);
                        if(mois < 10) {
                            consoAvantNum.setText("0" + mois + "-" + an);
                        }
                        else {
                            consoAvantNum.setText(mois + "-" + an);
                        }
                    }
                        break;
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

    /**
     * Création d'une liste de choix dans un dialog
     * @param choix Type de contenu de la liste (Région, Appellation, etc...)
     * @param dialog Dialog où on affiche la liste
     */
    private void listePopUp(final String choix, final Dialog dialog)
    {
        dialog.setContentView(R.layout.liste_choix_popup);
        ListView listeChoix = (ListView) dialog.findViewById(R.id.listeChoix);
        switch (choix)
        {
            case "region":
            {
                dialog.setTitle("Région");
                RegionAdapter regionAda = new RegionAdapter(mContext, R.layout.liste_choix_item, 0, ControleurPrincipal.listeRegion);
                listeChoix.setAdapter(regionAda);
            }
            break;
            case "appellation":
            {
                // On récupère l'id de la région pour afficher la liste des appellations correspondantes
                Integer idR = Integer.parseInt(idRegion.getText().toString());
                dialog.setTitle("Appellation");
                if (!idR.equals("") && idR != 6 && idR != 12)
                {
                    ArrayList<Appellation> listeAppellations = ControleurPrincipal.listeRegionAoc.get(idR);
                    AppellationAdapter aocAda = new AppellationAdapter(mContext, R.layout.liste_choix_item, R.id.nom, listeAppellations);
                    listeChoix.setAdapter(aocAda);
                }
            }
            break;
            case "achat":
            {
                dialog.setTitle("Lieu d'achat");
                LieuAchatAdapter achatAda = new LieuAchatAdapter(mContext, R.layout.liste_choix_item, R.id.nom, ControleurPrincipal.listeLieuAchat);
                listeChoix.setAdapter(achatAda);
            }
            break;
            case "stockage":
            {
                dialog.setTitle("Lieu de stockage");
                LieuStockageAdapter stockageAda = new LieuStockageAdapter(mContext, R.layout.liste_choix_item, R.id.nom, ControleurPrincipal.listeLieuStockage);
                listeChoix.setAdapter(stockageAda);
            }
            break;
            case "type":
            {
                dialog.setTitle("Type de vin");
                ArrayAdapter typeAda = new ArrayAdapter<String>(mContext, R.layout.liste_choix_item, R.id.nom, listeType)
                {
                    // Override pour police personnalisée
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                         return GestionListes.createListe(position, convertView, mContext, listeType, "type");
                    }

                    @Override
                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                         return GestionListes.createListe(position, convertView, mContext, listeType, "type");
                    }
                };
                listeChoix.setAdapter(typeAda);
            }
        }

        listeChoix.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                switch (choix)
                {
                    case "region":
                    {
                        if(position>0) {
                            region.setText(GestionListes.getNomRegion(position));
                        }
                        else {
                            region.setText("");
                        }
                        if(position == 6 || position == 12)
                        {
                            if(labelAppellation.isShown())
                            {
                                labelAppellation.setVisibility(View.INVISIBLE);
                                appellation.setVisibility(View.INVISIBLE);
                            }
                        }
                        else
                        {
                            labelAppellation.setVisibility(View.VISIBLE);
                            appellation.setVisibility(View.VISIBLE);
                        }

                        idRegion.setText(Integer.toString(position));
                    }
                    break;
                    case "appellation":
                    {
                        int idR = Integer.parseInt(idRegion.getText().toString());
                        if(position>0 && idR > 0) {
                            appellation.setText(GestionListes.getNomAppellation(position,idR));
                        }
                        else {
                            appellation.setText("");
                        }
                        idAppellation.setText(Integer.toString(position));
                    }
                    break;
                    case "achat":
                    {
                        if(position>0) {
                            lieuAchat.setText(GestionListes.getNomLieuAchat(position));
                        }
                        else {
                            lieuAchat.setText("");
                        }
                        idLieuAchat.setText(Integer.toString(position));
                    }
                    break;
                    case "stockage":
                    {
                        if(position>0) {
                            lieuStockage.setText(GestionListes.getNomLieuStockage(position));
                        }
                        else {
                            lieuStockage.setText("");
                        }
                        idLieuStockage.setText(Integer.toString(position));
                    }
                    break;
                    case "type":
                    {
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
