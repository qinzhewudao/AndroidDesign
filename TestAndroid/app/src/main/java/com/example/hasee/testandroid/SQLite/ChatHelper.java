package com.example.hasee.testandroid.SQLite;

/**
 * Created by hasee on 2017/7/14.
 */

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.example.hasee.testandroid.model.ChatModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatHelper extends SQLiteOpenHelper {
    ChatHelper a = this;
    private static final String DATABASE_NAME="test.db";//数据库名称
    private static final int SCHEMA_VERSION=2;//版本号,则是升级之后的,升级方法请看onUpgrade方法里面的判断
    private  SQLiteDatabase db;
    public ChatHelper(Context context) {//构造函数,接收上下文作为参数,直接调用的父类的构造函数
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);//第三个参数为自定义游标，比如一次移动多行
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS chat (icon STRING , context STRING,type STRING);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion==1 && newVersion==2) {//升级判断,如果再升级就要再加两个判断,从1到3,从2到3
            db.execSQL("ALTER TABLE chat ADD phone TEXT;");
        }
    }

    public Cursor getAll(String where, String orderBy) {//返回表中的数据,where是调用时候传进来的搜索内容,orderby是设置中传进来的列表排序类型
        StringBuilder buf=new StringBuilder("SELECT icon,context FROM chat");

        if (where!=null) {
            buf.append(" WHERE ");
            buf.append(where);
        }

        if (orderBy!=null) {
            buf.append(" ORDER BY ");
            buf.append(orderBy);
        }

        return(getReadableDatabase().rawQuery(buf.toString(), null));
    }

    public Cursor getByIcon(String icon) {//根据点击事件获取id,查询数据库
        String[] args={icon};

        return(getReadableDatabase()
                .rawQuery("SELECT icon,context FROM chat WHERE icon=?",
                        args));
    }

    public void insert(String icon, String context,String type) {
        ContentValues cv=new ContentValues();

        cv.put("icon", icon);
        cv.put("context", context);
        cv.put("type", type);
        Log.e("sy","插入的TYPE："+type);

        getWritableDatabase().insert("chat", "icon", cv);
    }

    public void update(String icon, String context)
    {
        ContentValues cv=new ContentValues();
        String[] args={icon};

        cv.put("context", context);


        getWritableDatabase().update("chat", cv, "icon=?",
                args);
    }

    private Cursor queryTheCursor() {
        db = a.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM chat", null);
        return c;
    }

    public ArrayList<ChatModel> query() {
        ArrayList<ChatModel> chat = new ArrayList<>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            ChatModel ChatModel = new ChatModel();
            ChatModel.icon = c.getString(c.getColumnIndex("icon"));
            ChatModel.content = c.getString(c.getColumnIndex("context"));
            ChatModel.type = c.getString(c.getColumnIndex("type"));
            chat.add(ChatModel);
        }
        c.close();
        return chat;
    }
    
    public String getIcon(Cursor c) {
        return(c.getString(1));
    }

    public String getContext(Cursor c) {
        return(c.getString(2));
    }

}
