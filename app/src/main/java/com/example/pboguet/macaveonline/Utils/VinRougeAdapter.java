package com.example.pboguet.macaveonline.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Activities.MyMainActivity;
import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.Class.VinRouge;
import com.example.pboguet.macaveonline.R;

import java.util.ArrayList;

/**
 * Created by pboguet on 19/06/15.
 */
public class VinRougeAdapter extends ArrayAdapter<VinRouge> {
    private ArrayList<VinRouge> vins;
    private Context mContext;

    public VinRougeAdapter(MyMainActivity context, int textViewResourceID, ArrayList<VinRouge> vins) {
        super(context,textViewResourceID,vins);
        mContext = context;
        this.vins = vins;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        Vin vin = vins.get(position);

        if (v == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = inflater.inflate(R.layout.liste_vins, null);

        }
        if(vins.size() > 0) {
            // TODO : récupérer l'id de la région et chercher l'intitulé dans la liste des régions
            TextView nom = (TextView) v.findViewById(R.id.nomVin);
            TextView region = (TextView) v.findViewById(R.id.regionVin);
            TextView annee = (TextView) v.findViewById(R.id.annee);
            TextView nbBt = (TextView) v.findViewById(R.id.nbBouteilles);
            TextView plus = (TextView) v.findViewById(R.id.plus);
            TextView moins = (TextView) v.findViewById(R.id.moins);

            if (nom != null) {
                nom.setText(vin.getNom());
                region.setText(Integer.toString(vin.getRegion()));
                annee.setText(Integer.toString(vin.getAnnee()));
                nbBt.setText(Integer.toString(vin.getNbBouteilles()));
                plus.setText("+");
                moins.setText("-");
            }
        }
        return v;
    }
}
