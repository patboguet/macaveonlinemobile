package com.example.pboguet.macaveonline.Utils.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.Appellation;
import com.example.pboguet.macaveonline.Class.Region;
import com.example.pboguet.macaveonline.R;

import java.util.ArrayList;

/**
 * Created by pboguet on 15/09/2015.
 */
public class AppellationAdapter extends ArrayAdapter<Appellation> {
    private ArrayList<Appellation> appellations;
    private Context mContext;
    private TextView id;
    private TextView nom;

    public AppellationAdapter(Context context, int layoutID, int textViewID, ArrayList liste) {
        super(context,layoutID,textViewID,liste);
        mContext = context;
        this.appellations = liste;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return constructionListe(position, convertView);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return constructionListe(position, convertView);
    }

    private View constructionListe(int position, View convertView) {
        View v = convertView;
        Appellation aoc = appellations.get(position);

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.liste_choix_item, null);
        }
        if(appellations.size() > 0) {
            id = (TextView) v.findViewById(R.id.id);
            nom = (TextView) v.findViewById(R.id.nom);
            nom.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/MaiandraGD.ttf"));
            if(id != null)
            {
                id.setText(Integer.toString(aoc.getId()));
                nom.setText(aoc.getNom());
            }
        }
        return v;
    }
}
