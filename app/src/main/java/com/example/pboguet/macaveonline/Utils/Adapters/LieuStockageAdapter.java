package com.example.pboguet.macaveonline.Utils.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.pboguet.macaveonline.Class.LieuStockage;
import com.example.pboguet.macaveonline.Utils.GestionListes;

import java.util.ArrayList;

/**
 * Created by pbogu_000 on 29/08/2015.
 */
public class LieuStockageAdapter extends ArrayAdapter<LieuStockage> {
    private ArrayList<LieuStockage> lieuxStockage;
    private Context mContext;

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return GestionListes.createListe(position, convertView, mContext, lieuxStockage, "lieuS");
    }

    public LieuStockageAdapter(Context context, int layoutID, int textViewResourceID, ArrayList<LieuStockage> lieux) {
        super(context,layoutID,textViewResourceID,lieux);
        mContext = context;
        this.lieuxStockage = lieux;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        return GestionListes.createListe(position, convertView, mContext, lieuxStockage, "lieuS");
    }
}
