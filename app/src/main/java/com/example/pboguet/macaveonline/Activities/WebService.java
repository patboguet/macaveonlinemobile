package com.example.pboguet.macaveonline.Activities;

import android.app.Activity;
import android.os.Bundle;

import com.example.pboguet.macaveonline.Utils.BackTask;

/**
 * Created by pboguet on 18/06/15.
 */
public class WebService extends Activity {

    private static Activity mActivity;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mActivity = this;
        new BackTask(mActivity).execute("select_vins");
    }

    public static Activity getInstance() {
        return mActivity;
    }
}

