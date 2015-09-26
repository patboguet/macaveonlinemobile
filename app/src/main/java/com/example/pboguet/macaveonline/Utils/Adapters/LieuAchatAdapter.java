package com.example.pboguet.macaveonline.Utils.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.LieuAchat;
import com.example.pboguet.macaveonline.R;
import com.example.pboguet.macaveonline.Utils.GestionListes;

import java.util.ArrayList;

/**
 * Created by pbogu_000 on 29/08/2015.
 */
public class LieuAchatAdapter extends ArrayAdapter<LieuAchat>
{
    private ArrayList<LieuAchat> lieuxAchat;
    private Context mContext;

    public LieuAchatAdapter(Context context, int textViewResourceID, int textViewID, ArrayList<LieuAchat> lieux)
    {
        super(context,textViewResourceID,textViewID,lieux);
        mContext = context;
        this.lieuxAchat = lieux;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        return GestionListes.createListe(position, convertView, mContext, lieuxAchat, "lieuA");
    }
}
