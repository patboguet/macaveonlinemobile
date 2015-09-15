package com.example.pboguet.macaveonline.Utils.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.LieuAchat;
import com.example.pboguet.macaveonline.R;

import java.util.ArrayList;

/**
 * Created by pbogu_000 on 29/08/2015.
 */
public class LieuAchatAdapter extends ArrayAdapter<LieuAchat> {
    private ArrayList<LieuAchat> lieuxAchat;
    private Context mContext;
    private TextView id;
    private TextView nom;

    public LieuAchatAdapter(Context context, int textViewResourceID, int textViewID, ArrayList<LieuAchat> lieux) {
        super(context,textViewResourceID,textViewID,lieux);
        mContext = context;
        this.lieuxAchat = lieux;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final LieuAchat lieu = lieuxAchat.get(position);
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.liste_choix_item, null);
        }
        if(lieuxAchat.size() > 0) {
            id = (TextView) v.findViewById(R.id.id);
            nom = (TextView) v.findViewById(R.id.nom);
            if(id != null)
            {
                id.setText(Integer.toString(lieu.getId()));
                nom.setText(lieu.getNom());
            }
        }
        return v;
    }
}
