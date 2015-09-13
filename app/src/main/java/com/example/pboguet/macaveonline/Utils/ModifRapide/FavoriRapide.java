package com.example.pboguet.macaveonline.Utils.ModifRapide;

import android.view.View;
import android.widget.ImageView;

import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.R;

/**
 * Created by Patrick on 13/09/2015.
 */
public class FavoriRapide implements View.OnClickListener {
    private Vin vin;
    private ImageView favori;

    public FavoriRapide(Vin vin, ImageView favori) {
        this.vin = vin;
        this.favori = favori;
    }

    @Override
    public void onClick(View v) {
        if(vin.isFavori()){
            vin.setFavori(false);
            favori.setImageResource(R.mipmap.ic_favori_no);
        }
        else {
            vin.setFavori(true);
            favori.setImageResource(R.mipmap.ic_favori_oui);
        }
    }
}
