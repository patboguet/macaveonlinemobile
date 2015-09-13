package com.example.pboguet.macaveonline.Utils.ModifRapide;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pboguet.macaveonline.Class.Vin;
import com.example.pboguet.macaveonline.R;

/**
 * Created by Patrick on 12/09/2015.
 */
public class NoteRapide implements View.OnClickListener {
    private Dialog dialog;
    private Vin vin;

    public NoteRapide(Vin vin, Dialog dialog) {
        this.vin = vin;
        this.dialog = dialog;
    }

    @Override
    public void onClick(View v) {
        dialog.setContentView(R.layout.note_rapide);
        dialog.setTitle("Note");
        final RatingBar rating = (RatingBar) dialog.findViewById(R.id.notation);
        final TextView noteVingt = (TextView) dialog.findViewById(R.id.noteVingt);
        Button valider = (Button) dialog.findViewById((R.id.valider));
        Button annuler = (Button) dialog.findViewById(R.id.annuler);
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                noteVingt.setText(Integer.toString((int) (rating*4)));
            }
        });
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vin.setNote(rating.getRating()*4);
                dialog.dismiss();
            }
        });
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
