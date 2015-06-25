package com.example.pboguet.macaveonline.Class;

import java.util.HashMap;

/**
 * Created by pboguet on 19/06/15.
 */
public class LieuAchat {
    public HashMap<Long, String> listeLieuAchat;

    public LieuAchat(long id, String lieu)
    {
        this.listeLieuAchat.put(id, lieu);
    }
}
