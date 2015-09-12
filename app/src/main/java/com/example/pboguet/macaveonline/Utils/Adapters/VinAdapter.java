package com.example.pboguet.macaveonline.Utils.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Activities.FicheVin;
import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.GestionListes;
import com.example.pboguet.macaveonline.Utils.NoteRapide;

import java.util.ArrayList;

/**
 * Created by pboguet on 19/06/15.
 */
public class VinAdapter extends ArrayAdapter<Vin> {
    private ArrayList<Vin> vins;
    private Context mContext;
    private VinAdapter adapter;
    private TextView id;
    private TextView nom;
    private TextView region;
    private TextView annee;
    private TextView nbBt;
    private ImageView note;
    private ImageView favori;
    private TextView plus;
    private TextView moins;
    private Dialog dialog;
    private boolean[] coeurs;

    public VinAdapter(Context context, int textViewResourceID, ArrayList<Vin> vins) {
        super(context,textViewResourceID,vins);
        adapter = this;
        mContext = context;
        this.vins = vins;
        this.dialog = new Dialog(context);
        this.coeurs = new boolean[vins.size()];
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final Vin vin = vins.get(position);
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.liste_vins, null);
        }
        if(vins.size() > 0) {
            id = (TextView) v.findViewById(R.id.idVin);
            nom = (TextView) v.findViewById(R.id.nomVin);
            region = (TextView) v.findViewById(R.id.regionVin);
            annee = (TextView) v.findViewById(R.id.annee);
            nbBt = (TextView) v.findViewById(R.id.nbBouteilles);
            note = (ImageView) v.findViewById((R.id.note));
            favori = (ImageView) v.findViewById((R.id.favori));
            plus = (TextView) v.findViewById(R.id.plus);
            moins = (TextView) v.findViewById(R.id.moins);


            if (nom != null) {
                id.setText(Integer.toString(vin.getIdVin()));
                nom.setText(vin.getNom());
                region.setText(GestionListes.getNomRegion(vin.getRegion()));
                annee.setText(Integer.toString(vin.getAnnee()));
                nbBt.setText(Long.toString(vin.getNbBouteilles()));
                if(vin.isFavori() || coeurs[position])
                {
                    favori.setImageResource(R.mipmap.ic_favori_oui);

                }
                else
                    favori.setImageResource(R.mipmap.ic_favori_no);
                plus.setText("+");
                moins.setText("-");
            }
        }
        RelativeLayout rl = (RelativeLayout) v.findViewById(R.id.nomRegAn);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FicheVin.class);
                intent.putExtra("Vin", vin);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
   
        note.setOnClickListener(new NoteRapide(vin));

        favori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vin.isFavori()){
                    vin.setFavori(false);
                    favori.setImageResource(R.mipmap.ic_favori_no);
                }
                else {
                    vin.setFavori(true);
                    favori.setImageResource(R.mipmap.ic_favori_oui);
                }
            }
        });

        return v;
    }
}
