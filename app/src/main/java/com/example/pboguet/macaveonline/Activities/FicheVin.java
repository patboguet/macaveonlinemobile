package com.example.pboguet.macaveonline.Activities;


import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
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

import com.example.pboguet.macaveonline.Class.ControleurPrincipal;
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
    private EditText commentaires;
    private Button modifier;
    private Button supprimer;
    private Button copier;
    private Button annuler;
    private ListView menu;
    private Dialog dialog;
    private static Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = this;
        setContentView(R.layout.detail_vin);

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
        if(vin.getConsoPartir() != null) {
            String[] consoP = vin.getConsoPartir().split("-");
            consoPartir.setText(consoP[1]+"/"+consoP[2]);
        }
        else
            consoPartir.setText(vin.getConsoPartir());

        if(vin.getConsoAvant() != null) {
            String[] consoA = vin.getConsoAvant().split("-");
            consoAvant.setText(consoA[1]+"/"+consoA[2]);
        }
        else
            consoAvant.setText(vin.getConsoAvant());

        lieuAchat.setText(GestionListes.getNomLieuAchat(vin.getLieuAchat()));
        lieuStockage.setText(GestionListes.getNomLieuStockage(vin.getLieuStockage()));
        note.setRating(vin.getNote() / 4);
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

        /*region.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.liste_choix_popup);
                dialog.setTitle("Région");
                ListView listeChoix = (ListView) dialog.findViewById(R.id.listeChoix);
                ArrayAdapter regionAda = new RegionAdapter(getApplicationContext(),R.layout.liste_choix_item, ControleurPrincipal.listeRegion);
                listeChoix.setAdapter(regionAda);
                listeChoix.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        region.setText(GestionListes.getNomRegion(position + 1));
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        annee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.liste_annees);
                dialog.setTitle("Année");
                Button valider = (Button) dialog.findViewById(R.id.validerDate);
                Button annuler = (Button) dialog.findViewById(R.id.annuler);
                final DatePicker listeAnnee = (DatePicker) dialog.findViewById(R.id.datePicker);
                // on cache le jour et le mois ansi que le calendrier
                ((ViewGroup) listeAnnee).findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
                ((ViewGroup) listeAnnee).findViewById(Resources.getSystem().getIdentifier("month", "id", "android")).setVisibility(View.GONE);
                listeAnnee.setCalendarViewShown(false);

                valider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        annee.setText(Integer.toString(listeAnnee.getYear()));
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
        });
        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.liste_choix_popup);
                dialog.setTitle("Type de vin");
                ListView listeChoix = (ListView) dialog.findViewById(R.id.listeChoix);
                final ArrayList listeType = new ArrayList(4);
                listeType.add(0,"Blanc");
                listeType.add(1, "Rouge");
                listeType.add(2, "Rosé");
                listeType.add(3, "Mousseux");
                ArrayAdapter typeAda = new ArrayAdapter<String>(getApplicationContext(),R.layout.liste_types,R.id.nomType, listeType);
                listeChoix.setAdapter(typeAda);
                listeChoix.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        type.setText((CharSequence) listeType.get(position));
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });*/

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
                dialog.setContentView(R.layout.liste_choix_popup);
                dialog.setTitle("Type de vin");
                ListView listeChoix = (ListView) dialog.findViewById(R.id.listeChoix);
                ArrayAdapter achatAda = new LieuAchatAdapter(getApplicationContext(),R.layout.liste_choix_item,ControleurPrincipal.listeLieuAchat);
                listeChoix.setAdapter(achatAda);
                listeChoix.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        lieuAchat.setText(GestionListes.getNomLieuAchat(position + 1));
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        lieuStockage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.liste_choix_popup);
                dialog.setTitle("Lieu stockage");
                ListView listeChoix = (ListView) dialog.findViewById(R.id.listeChoix);
                ArrayAdapter stockageAda = new LieuStockageAdapter(getApplicationContext(),R.layout.liste_choix_item,ControleurPrincipal.listeLieuStockage);
                listeChoix.setAdapter(stockageAda);
                listeChoix.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        lieuStockage.setText(GestionListes.getNomLieuStockage(position + 1));
                        dialog.dismiss();
                    }
                });
                dialog.show();
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

                //String deg = degre.getText().toString();
                String nb = nbBouteilles.getText().toString();
                String prixA = prix.getText().toString();

                /*vin.setNom(nom.getText().toString());
                //vin.setAnnee(Integer.parseInt(annee.getText().toString()));
                //vin.setRegion(GestionListes.getIdRegion(region.getText().toString()));
                //appellation
               // vin.setType(type.getText().toString());
                if(deg.isEmpty())
                    vin.setDegreAlcool(0);
                else
                    vin.setDegreAlcool(Float.parseFloat(deg));*/
                vin.setLieuStockage(GestionListes.getIdLieuStockage(lieuStockage.getText().toString()));
                vin.setLieuAchat(GestionListes.getIdLieuAchat(lieuAchat.getText().toString()));
                vin.setConsoPartir(consoPartir.getText().toString());
                vin.setConsoAvant(consoAvant.getText().toString());
                vin.setNote(note.getRating() * 4);
                if(nb.isEmpty())
                    vin.setNbBouteilles(0);
                else
                    vin.setNbBouteilles(Integer.parseInt((nb)));
                vin.setSuiviStock(suivi.isChecked());
                vin.setFavori(favori.isChecked());
                if(prixA.isEmpty())
                    vin.setPrixAchat(0);
                else
                    vin.setPrixAchat(Float.parseFloat(prixA));
                vin.setOffertPar(offert.getText().toString());
                vin.setCommentaires(commentaires.getText().toString());
                WebService.updateVin(vin);
            }
        });

        // COPIER VIN

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
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
                    consoPartir.setText("01/" + Integer.toString(listeAnnee.getMonth() + 1) + "/" + Integer.toString(listeAnnee.getYear()));
                } else {
                    consoAvant.setText("01/" + Integer.toString(listeAnnee.getMonth() + 1) + "/" + Integer.toString(listeAnnee.getYear()));
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

    public static Activity getActivity() {
        return mActivity;
    }
}


