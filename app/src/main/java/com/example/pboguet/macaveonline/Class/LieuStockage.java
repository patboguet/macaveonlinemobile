package com.example.pboguet.macaveonline.Class;

import java.util.HashMap;

/**
 * Created by pboguet on 19/06/15.
 */
public class LieuStockage {
    public HashMap<Long, String> listeLieuStockage;

    public LieuStockage(long id, String lieu)
    {
        this.listeLieuStockage.put(id, lieu);
    }
}
