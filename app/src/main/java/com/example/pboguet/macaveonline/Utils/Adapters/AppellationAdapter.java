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
import com.example.pboguet.macaveonline.Utils.GestionListes;

import java.util.ArrayList;

/**
 * Created by pboguet on 15/09/2015.
 */
public class AppellationAdapter extends ArrayAdapter<Appellation>
{
    private ArrayList<Appellation> appellations;
    private Context mContext;

    public AppellationAdapter(Context context, int layoutID, int textViewID, ArrayList liste)
    {
        super(context,layoutID,textViewID,liste);
        mContext = context;
        this.appellations = liste;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return GestionListes.createListe(position, convertView, mContext, appellations, "aoc");
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return GestionListes.createListe(position, convertView, mContext, appellations, "aoc");
    }

}
