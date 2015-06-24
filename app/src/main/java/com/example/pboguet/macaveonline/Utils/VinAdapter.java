package com.example.pboguet.macaveonline.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.R;

import java.util.List;

/**
 * Created by pboguet on 19/06/15.
 */
public class VinAdapter extends ArrayAdapter<String> {
    private List<Vin> listeVin;
    private final Context context;
    private final String[] values1;
    private final String[] values2;

    public VinAdapter(Context context, String[] object1, String[] object2) {
        super(context, R.layout.ma_cave, object2);
        this.context = context;
        this.values1 = object1;
        this.values2 = object2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.ma_cave, parent, false);

        TextView textView1 = (TextView) rowView.findViewById(R.id.nomVin);
        TextView textView2 = (TextView) rowView.findViewById(R.id.annee);
        textView1.setText(values1[position]);
        textView2.setText(values2[position]);

        return rowView;
    }

    /*public VinAdapter(List<Vin> listeVin, Context ctx) {
        super(ctx, android.R.layout.simple_list_item_1, listeVin);
        this.listeVin = listeVin;
        this.context = ctx;
    }

    public int getCount() {
        if (listeVin != null)
            return listeVin.size();
        return 0;
    }

    public Vin getItem(int position) {
        if (listeVin != null)
            return listeVin.get(position);
        return null;
    }

    public long getItemId(int position) {
        if (listeVin != null)
            return listeVin.get(position).hashCode();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.liste_vins, null);
        }

        Vin vin = listeVin.get(position);
        TextView nomVin = (TextView) v.findViewById(R.id.nomVin);
        nomVin.setText(vin.getNom());

        TextView region = (TextView) v.findViewById(R.id.typeVin);
        region.setText((CharSequence) vin.getAppellation());

        TextView annee = (TextView) v.findViewById(R.id.annee);
        annee.setText(vin.getAnnee());

        TextView nbBouteilles = (TextView) v.findViewById(R.id.nbBouteilles);
        nbBouteilles.setText(vin.getAnnee());

        return v;

    }

    public List<Vin> getItemList() {
        return listeVin;
    }

    public void setItemList(List<Vin> itemList) {
        this.listeVin = listeVin;
    }*/
}
