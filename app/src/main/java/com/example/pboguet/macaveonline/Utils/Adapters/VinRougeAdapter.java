package com.example.pboguet.macaveonline.Utils.Adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pboguet.macaveonline.Activities.FicheVin;
import com.example.pboguet.macaveonline.Activities.MyMainActivity;
import com.example.pboguet.macaveonline.Class.ControleurPrincipal;
import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.Class.VinRouge;
import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.GestionListes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by pboguet on 19/06/15.
 */
public class VinRougeAdapter extends ArrayAdapter<VinRouge> {
    private ArrayList<VinRouge> vins;
    private Context mContext;
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

    public VinRougeAdapter(Context context, int textViewResourceID, ArrayList<VinRouge> vins) {
        super(context,textViewResourceID,vins);
        mContext = context;
        this.vins = vins;
        this.dialog = new Dialog(context);
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
            plus = (TextView) v.findViewById(R.id.plus);
            moins = (TextView) v.findViewById(R.id.moins);


            if (nom != null) {
                id.setText(Integer.toString(vin.getIdVin()));
                nom.setText(vin.getNom());
                region.setText(GestionListes.getNomRegion(vin.getRegion()));
                annee.setText(Integer.toString(vin.getAnnee()));
                nbBt.setText(Long.toString(vin.getNbBouteilles()));
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
                mContext.startActivity(intent);
            }
        });
   
        note.setOnClickListener(new View.OnClickListener() {
            int id = vin.getIdVin();
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.note_rapide);
                dialog.setTitle("Note");
                final RatingBar rating = (RatingBar) dialog.findViewById(R.id.notation);
                final TextView noteVingt = (TextView) dialog.findViewById(R.id.noteVingt);
                Button valider = (Button) dialog.findViewById((R.id.valider));
                Button annuler = (Button) dialog.findViewById(R.id.annuler);
                rating.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        noteVingt.setText(Float.toString(rating.getRating()*4));
                    }
                });
                valider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Vin vin = GestionListes.getVinById(id, 2);
                        vin.setNote(rating.getRating()*4);
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
   

        return v;
    }

}