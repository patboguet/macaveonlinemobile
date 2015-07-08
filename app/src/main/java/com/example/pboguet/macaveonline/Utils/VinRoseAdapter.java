package com.example.pboguet.macaveonline.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Activities.MyMainActivity;
import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.Class.VinRose;
import com.example.pboguet.macaveonline.R;

import java.util.ArrayList;

/**
 * Created by pboguet on 06/07/2015.
 */
public class VinRoseAdapter extends ArrayAdapter<VinRose> {

    private ArrayList<VinRose> vins;
    private Context mContext;

    public VinRoseAdapter(MyMainActivity context, int textViewResourceID, ArrayList<VinRose> vins) {
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

        // TODO : r�cup�rer l'id de la r�gion et chercher l'intitul� dans la liste des r�gions
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

        return v;
    }
}
