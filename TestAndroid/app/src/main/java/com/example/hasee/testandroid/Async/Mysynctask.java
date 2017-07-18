package com.example.hasee.testandroid.Async;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import android.widget.ImageView;
/**
 * Created by hasee on 2017/7/18.
 */

public class Mysynctask extends AsyncTask<String, Void, Bitmap> {
        private ImageView imageView ;
        private ProgressDialog dialog ;

        public Mysynctask(ImageView imageView,ProgressDialog dialog) {
            this.imageView = imageView;
            this.dialog = dialog;
        }

        @Override
        //在界面上显示进度条
        protected void onPreExecute() {
            dialog.show() ;
        };

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = downloadimage(params[0]);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
            dialog.dismiss();
        }

        //上面的方法中，第一个参数：网络图片的路径，第二个参数的包装类：进度的刻度，第三个参数：任务执行的返回结果

        public Bitmap downloadimage(String uri) {  //三个点，代表可变参数
            //使用网络链接类HttpClient类完成对网络数据的提取
            try {
                URL url = new URL(uri);
                URLConnection rulConnection = url.openConnection();
                HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
                rulConnection.connect();
                if (httpUrlConnection.getResponseCode() == 200) {
                    Bitmap bitmap = BitmapFactory.decodeStream(httpUrlConnection.getInputStream());
                    return bitmap;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

}