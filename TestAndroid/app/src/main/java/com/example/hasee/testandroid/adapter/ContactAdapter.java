package com.example.hasee.testandroid.adapter;

/**
 * Created by hasee on 2017/7/14.
 */

import android.appwidget.AppWidgetHostView;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hasee.testandroid.R;
import com.example.hasee.testandroid.model.ContactModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public  class ContactAdapter  extends BaseAdapter{

    private List<ContactModel> mData;       //创建ContactModel类型的List表
    private LayoutInflater mInflater;               //定义线性布局过滤器

    public ContactAdapter(Context context , List<ContactModel> data){
        this.mData = data ;
        mInflater = LayoutInflater.from(context);       //获取布局
    }
    /**
     * 得到列表长度
     * @return
     */
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;    //得到子项位置id
    }
    /**
     * 获取列表对应位置的子项
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    /**
     * 获取对应Positon的type值
     * @param position
     * @return
     */

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        //判断是否缓存
        if(convertView == null){
                holder = new ViewHolder();
                //通过LayoutInflater实例化布局
                convertView = mInflater.inflate(R.layout.contact, null);
                //绑定id
                holder.contactimage = (ImageView) convertView.findViewById(R.id.contactimage);
                holder.contacttext = (TextView) convertView.findViewById(R.id.contacttext);
                holder.contactredpoint = (TextView) convertView.findViewById(R.id.contactredpoint);

                convertView.setTag(holder);         //为View设置tag
        }
        else{
            holder  =   (ViewHolder)convertView.getTag();      //通过tag找到缓存的布局
        }
        //设置布局中控件要显示的视图
        holder.contactimage.setImageBitmap(mData.get(position).getContactimage());
        holder.contacttext.setText(mData.get(position).getContent());
        holder.contactredpoint.setText(mData.get(position).getMsgcount());
        return convertView;     //返回一个view
    }

    /**
     * 实体类
     */
    public final class ViewHolder{
        public ImageView contactimage;
        public TextView contactredpoint;
        public TextView contacttext;
    }

}
