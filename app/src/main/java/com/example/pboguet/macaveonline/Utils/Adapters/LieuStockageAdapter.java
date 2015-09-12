package com.example.pboguet.macaveonline.Utils.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.LieuStockage;
import com.example.pboguet.macaveonline.R;

import java.util.ArrayList;

/**
 * Created by pbogu_000 on 29/08/2015.
 */
public class LieuStockageAdapter extends ArrayAdapter<LieuStockage> {
    private ArrayList<LieuStockage> lieuxStockage;
    private Context mContext;
    private TextView id;
    private TextView nom;


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return constructionListe(position, convertView);
    }

    public LieuStockageAdapter(Context context, int layoutID, int textViewResourceID, ArrayList<LieuStockage> lieux) {
        super(context,layoutID,textViewResourceID,lieux);
        mContext = context;
        this.lieuxStockage = lieux;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        return constructionListe(position, convertView);
    }

    public View constructionListe(final int position, View convertView){
        View v = convertView;
        final LieuStockage lieu = lieuxStockage.get(position);
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.liste_choix_item, null);
        }
        if(lieuxStockage.size() > 0) {
            id = (TextView) v.findViewById(R.id.id);
            nom = (TextView) v.findViewById(R.id.nom);
            if(id != null)
            {
                id.setText(Long.toString(lieu.getId()));
                nom.setText(lieu.getNom());
            }
        }
        return v;
    }
}
