package com.example.pboguet.macaveonline.Utils.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.ControleurPrincipal;
import com.example.pboguet.macaveonline.R;

import java.util.ArrayList;

/**
 * Created by pboguet on 24/09/2015.
 */
public class MenuAdapter extends ArrayAdapter<String>
{
    private Context mContext;
    public MenuAdapter(Context c, ArrayList<String> menu)
    {
        super(c, R.layout.nom_menu, menu);
        this.mContext = c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.nom_menu, parent, false);
        TextView nomMenu = (TextView) v.findViewById(R.id.nomMenu);
        nomMenu.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/MaiandraGD.ttf"));
        ImageView iconeMenu = (ImageView) v.findViewById(R.id.iconeMenu);
        nomMenu.setText(ControleurPrincipal.menu.get(position));
        String s = ControleurPrincipal.menu.get(position);
        if (s.equals("Ma Cave")) {
            iconeMenu.setImageResource(R.mipmap.ic_cave);
        }
        else if(s.equals("Recherche")) {
            iconeMenu.setImageResource(R.mipmap.ic_search);
        }
        else {
            iconeMenu.setImageResource(R.mipmap.ic_ajout);
        }
        return v;
    }
}
