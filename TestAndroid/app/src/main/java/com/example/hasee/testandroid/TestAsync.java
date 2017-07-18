package com.example.hasee.testandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.app.ProgressDialog;
import com.example.hasee.testandroid.Async.Mysynctask;

public class TestAsync extends AppCompatActivity {

        private Button btn1;
        private ImageView img1;
        private Mysynctask mysynctask;
        private ProgressDialog dialog ;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_test_async);
            //添加弹出的对话框
            dialog = new ProgressDialog(this) ;
            dialog.setTitle("提示") ;
            dialog.setMessage("正在下载图片，请稍后···") ;

            btn1 = (Button) findViewById(R.id.btn1);
            img1 = (ImageView) findViewById(R.id.img1);
            mysynctask = new Mysynctask(img1,dialog);
            btn1.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    try{
                        String imgUrl = "http://www.scdakj.com/skin/img/bd116220721.jpg";
                        mysynctask.execute(imgUrl);
                    }catch (Exception e)
                    {
                        Log.e("sy","sync错误：");
                    }

                }
            });
        }

    }
