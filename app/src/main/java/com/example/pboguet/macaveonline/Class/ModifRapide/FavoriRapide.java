package com.example.pboguet.macaveonline.Class.ModifRapide;

import android.view.View;
import android.widget.ImageView;

import com.example.pboguet.macaveonline.Activities.WebService;
import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.R;

/**
 * Ajout / Suppression rapide d'un vin en tant que favori sur mini fiche du vin
 * Created by Patrick on 13/09/2015.
 */
public class FavoriRapide implements View.OnClickListener
{
    private Vin vin;
    private ImageView favori;

    public FavoriRapide(Vin vin, ImageView favori)
    {
        this.vin = vin;
        this.favori = favori;
    }

    @Override
    public void onClick(View v)
    {
        if(vin.isFavori())
        {
            vin.setFavori(false);
            favori.setImageResource(R.mipmap.ic_favori_no);
        }
        else
        {
            vin.setFavori(true);
            favori.setImageResource(R.mipmap.ic_favori_oui);
        }
        // enregistrement de la modification
        WebService.updateVin(vin);
    }
}
