package com.example.hasee.testandroid.model;

import java.io.Serializable;

/**
 * Created by hasee on 2017/7/13.
 */
public class ChatModel implements Serializable{
    public String icon="";
    public String content="";
    public String type="";

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
