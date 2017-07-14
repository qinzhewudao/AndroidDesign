package com.example.hasee.testandroid.adapter;

/**
 * Created by hasee on 2017/7/13.
 */
/*import android.appwidget.AppWidgetHostView;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public  class Contacts extends BaseAdapter{

    ArrayList mDatalist = new ArrayList();
    Context acontext;
    public Contacts(Context context,ArrayList list)
    {
        acontext = context;
        mDatalist = list;
    }
    @Override
    public int getCount() {
        return mDatalist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
           convertView = creteView();
        }
        bindView(convertView,position);
        return convertView;
    }

    private ViewGroup creteView(){
        ViewHolder vh = new ViewHolder() ;

        ViewGroup convertView = new LinearLayout(acontext);
        LayoutInflater vi = (LayoutInflater) acontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //vi.inflate(R.layout.,convertView,true);

        TextView t1 = new TextView();
        TextView t2 = new TextView();
        vh.t1 = t1;
        vh.t2 = t2;

        convertView.setTag(vh);
        return  convertView;
    }

    private void bindView(ViewGroup view,int position)
    {
        mDatalist.get(position);

    }
    private class ViewHolder{
        TextView t1;
        TextView t2;
    }
}*/
