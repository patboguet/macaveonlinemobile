package com.example.pboguet.macaveonline.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.R;

import java.util.List;

/**
 * Created by pboguet on 19/06/15.
 */
public class SimpleAdapter extends ArrayAdapter<Vin> {
    private List<Vin> listeVin;
    private Context context;

    public SimpleAdapter(List<Vin> listeVin, Context ctx) {
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
            v = inflater.inflate(R.layout.list_item, null);
        }

        Vin v = listeVin.get(position);
        TextView text = (TextView) v.findViewById(R.id.name);
        text.setText(c.getName());

        TextView text1 = (TextView) v.findViewById(R.id.surname);
        text1.setText(c.getSurname());

        TextView text2 = (TextView) v.findViewById(R.id.email);
        text2.setText(c.getEmail());

        TextView text3 = (TextView) v.findViewById(R.id.phone);
        text3.setText(c.getPhoneNum());

        return v;

    }

    public List<Vin> getItemList() {
        return listeVin;
    }

    public void setItemList(List<Vin> itemList) {
        this.listeVin = listeVin;
    }
}
