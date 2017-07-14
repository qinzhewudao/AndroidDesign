package com.example.hasee.testandroid;

import android.app.Application;

/**
 * Created by hasee on 2017/7/13.
 */

public class testapplication extends Application {
    private static final String VALUE = "SheYang";

    private String value;

    @Override
    public void onCreate()
    {
        super.onCreate();
        setValue(VALUE); // 初始化全局变量
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
