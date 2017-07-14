package com.example.hasee.testandroid.model;

/**
 * Created by hasee on 2017/7/13.
 */
import android.content.Context;
import android.view.View;

import java.io.Serializable;

public class ItemModel implements Serializable {

    public static final int CHAT_A = 1001;
    public static final int CHAT_B = 1002;
    public int type;
    public Object object;

    public ItemModel(int type, Object object) {
        this.type = type;
        this.object = object;
    }
}