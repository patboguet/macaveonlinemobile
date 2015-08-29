package com.example.pboguet.macaveonline.Utils.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    private TextView nom;
    private TextView region;
    private TextView annee;
    private TextView nbBt;
    private TextView plus;
    private TextView moins;

    public VinRougeAdapter(Context context, int textViewResourceID, ArrayList<VinRouge> vins) {
        super(context,textViewResourceID,vins);
        mContext = context;
        this.vins = vins;
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
            nom = (TextView) v.findViewById(R.id.nomVin);
            region = (TextView) v.findViewById(R.id.regionVin);
            annee = (TextView) v.findViewById(R.id.annee);
            nbBt = (TextView) v.findViewById(R.id.nbBouteilles);
            plus = (TextView) v.findViewById(R.id.plus);
            moins = (TextView) v.findViewById(R.id.moins);

            if (nom != null) {
                nom.setText(vin.getNom());
                region.setText(GestionListes.getNomRegion(vin.getRegion()));
                annee.setText(Integer.toString(vin.getAnnee()));
                nbBt.setText(Integer.toString(vin.getNbBouteilles()));
                plus.setText("+");
                moins.setText("-");
            }
        }
        RelativeLayout rl = (RelativeLayout) v.findViewById(R.id.nomRegAn);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext.getApplicationContext(), "Click " + position, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), FicheVin.class);
                intent.putExtra("Vin", vin);
                mContext.startActivity(intent);
            }
        });

        return v;
    }

}
