package com.example.pboguet.macaveonline.Utils.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.Region;
import com.example.pboguet.macaveonline.R;

import java.util.ArrayList;

/**
 * Created by pbogu_000 on 29/08/2015.
 */
public class RegionAdapter extends ArrayAdapter<Region> {
        private ArrayList<Region> regions;
        private Context mContext;
        private TextView id;
        private TextView nom;


    public RegionAdapter(Context context, int textViewResourceID, ArrayList<Region> regions) {
        super(context,textViewResourceID,regions);
        mContext = context;
        this.regions = regions;
    }

    @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View v = convertView;
            final Region reg = regions.get(position);
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.liste_regions, null);
            }
            if(regions.size() > 0) {
                id = (TextView) v.findViewById(R.id.idRegion);
                nom = (TextView) v.findViewById(R.id.nomRegion);
                if(id != null)
                {
                    id.setText(Long.toString(reg.getId()));
                    nom.setText(reg.getNom());
                }
            }
            return v;
        }
}
