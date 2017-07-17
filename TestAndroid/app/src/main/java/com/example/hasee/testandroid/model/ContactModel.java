package com.example.hasee.testandroid.model;

import android.graphics.Bitmap;

/**
 * Created by hasee on 2017/7/14.
 */

public class ContactModel {

    private Bitmap contactimage;
    private String content="";
    private String msgcount="";

    public ContactModel() {
    }

    public Bitmap getContactimage() {
        return contactimage;
    }

    public void setContactimage(Bitmap contactimage) {
        this.contactimage = contactimage;
    }

    public String getMsgcount() {
        return msgcount;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMsgcount(String msgcount) {
        this.msgcount = msgcount;
    }

    public String getContent() {
        return content;
    }

}
