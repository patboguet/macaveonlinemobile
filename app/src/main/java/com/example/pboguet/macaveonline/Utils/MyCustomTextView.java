package com.example.pboguet.macaveonline.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * TextView personnalisé
 * Created by pboguet on 24/09/2015.
 */
public class MyCustomTextView extends TextView
{
    public MyCustomTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/MaiandraGD.ttf"));
    }
}
