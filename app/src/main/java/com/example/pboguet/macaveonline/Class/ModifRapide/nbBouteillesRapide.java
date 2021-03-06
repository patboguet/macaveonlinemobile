package com.example.pboguet.macaveonline.Class.ModifRapide;

import android.view.View;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Activities.WebService;
import com.example.pboguet.macaveonline.Class.Vin;

/**
 * Changement rapide du nombre de bouteilles
 * Created by Patrick on 13/09/2015.
 */
public class nbBouteillesRapide implements View.OnClickListener
{
    private TextView nbBt;
    private Vin vin;
    private String signe;

    public nbBouteillesRapide(Vin vin, String s, TextView nbBt)
    {
        this.vin = vin;
        this.signe = s;
        this.nbBt = nbBt;
    }

    @Override
    public void onClick(View v)
    {
        int nb = vin.getNbBouteilles();
        if(signe.equals("+"))
        {
            vin.setNbBouteilles(nb+1);
            nbBt.setText(Integer.toString(nb+1));
        }
        if(signe.equals("-") && nb > 0)
        {
            vin.setNbBouteilles(nb-1);
            nbBt.setText(Integer.toString(nb-1));
        }
        WebService.updateVin(vin);
    }
}
