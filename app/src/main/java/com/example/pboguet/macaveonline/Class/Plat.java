package com.example.pboguet.macaveonline.Class;

import java.util.HashMap;

/**
 * Created by pboguet on 19/06/15.
 */
public class Plat {
    public HashMap<Long, String> listePlat;

    public Plat(long id, String type) {
        this.listePlat = new HashMap<Long, String>();
        this.listePlat.put(id, type);
    }
}
