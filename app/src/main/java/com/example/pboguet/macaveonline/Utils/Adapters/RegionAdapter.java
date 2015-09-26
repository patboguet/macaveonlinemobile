package com.example.pboguet.macaveonline.Utils.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.Region;
import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.GestionListes;

import java.util.ArrayList;

/**
 * Created by pbogu_000 on 29/08/2015.
 */
public class RegionAdapter extends ArrayAdapter<Region>
{
    private ArrayList<Region> regions;
    private Context mContext;

    public RegionAdapter(Context context, int layoutID, int textViewID, ArrayList<Region> regions)
    {
        super(context,layoutID,textViewID,regions);
        mContext = context;
        this.regions = regions;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return GestionListes.createListe(position, convertView, mContext, regions, "region");
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       return GestionListes.createListe(position, convertView, mContext, regions, "region");
    }

}
