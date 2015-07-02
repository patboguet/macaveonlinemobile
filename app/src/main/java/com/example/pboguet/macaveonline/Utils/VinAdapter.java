package com.example.pboguet.macaveonline.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.Class.VinRouge;
import com.example.pboguet.macaveonline.R;

import java.util.ArrayList;

/**
 * Created by pboguet on 19/06/15.
 */
public class VinAdapter extends ArrayAdapter<Vin> {
    private ArrayList<Vin> vins;
    private Context mContext;

    public VinAdapter(Context context, int textViewResourceID, ArrayList<Vin> vins) {
        super(context,textViewResourceID,vins);
        mContext = context;
        this.vins = vins;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        Vin vin = vins.get(position);

        if (v == null) {

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = inflater.inflate(R.layout.liste_vins, null);

        }
        TextView nom = (TextView)v.findViewById(R.id.nomVin);
        TextView type = (TextView)v.findViewById(R.id.typeVin);
        TextView annee = (TextView)v.findViewById(R.id.annee);
        TextView nbBt = (TextView)v.findViewById(R.id.nbBouteilles);
        TextView plus = (TextView)v.findViewById(R.id.plus);
        TextView moins = (TextView)v.findViewById(R.id.moins);

        if (nom != null) {
            nom.setText(vin.getNom());
            type.setText(vin.getType());
            annee.setText(vin.getAnnee());
            nbBt.setText(vin.getNbBouteilles());
            plus.setText("+");
            moins.setText("-");
        }
        return v;
    }
}
