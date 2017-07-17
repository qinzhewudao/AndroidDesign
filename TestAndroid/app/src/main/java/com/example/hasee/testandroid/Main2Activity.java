package com.example.hasee.testandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.hasee.testandroid.Service.MyService;

import java.io.File;

public class Main2Activity extends AppCompatActivity {

    AppCompatActivity a = this;

    //为日志工具设置标签
    private static String TAG = "MusicService";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        TextView loginbutton = (TextView) findViewById(R.id.loginbutton);
        final EditText account = (EditText) findViewById(R.id.account);
        final EditText password = (EditText) findViewById(R.id.password);

        try
        {
            testapplication app = (testapplication) getApplication();
            Log.e("sy","get application name:"+app.getValue());
        }
        catch(Exception e)
        {
            Log.e("sy","has error"+ " "+e);

        }

        //sharedprefenrence练习
        File file= new File("/data/data/"+getPackageName().toString()+"/shared_prefs","account.xml");

        if(!file.exists()){

            SharedPreferences sharedPreferences = getSharedPreferences("account", Context.MODE_PRIVATE); //私有数据
            final SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
            editor.putString("account", "sheyang");
            editor.putString("password", "sheyang");
            editor.commit();//提交修改

        }


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View z) {
                Intent intent = new Intent(a,MainActivity.class);

                SharedPreferences share=getSharedPreferences("account",Context.MODE_PRIVATE);
                int i=share.getInt("i",0);
                String a=share.getString("account","");
                String p=share.getString("password","");

                if(account.getText().toString().equals(a) && password.getText().toString().equals(p))
                {

                    intent.putExtra("account", account.getText().toString());
                    intent.putExtra("password", password.getText().toString());
                    startActivity(intent);
                    Log.i("sy","i am continue");
                    finish();
                }
                else
                {
                    Toast.makeText(Main2Activity.this, "账户或者密码错误，请重新输入！"
                            , Toast.LENGTH_SHORT).show();
                }


            }
        });

        //输出Toast消息和日志记录
        Toast.makeText(this, "Main2Activity",
                Toast.LENGTH_SHORT).show();
        Log.e(TAG, "Main2Activity");

        initlizeViews();
    }

    private void initlizeViews(){
        Button btnStart = (Button)findViewById(R.id.startMusic);
        Button btnStop = (Button)findViewById(R.id.stopMusic);
        Button btnBind = (Button)findViewById(R.id.bindMusic);
        Button btnUnbind = (Button)findViewById(R.id.unbindMusic);

        //定义点击监听器
        OnClickListener ocl = new OnClickListener() {

            @Override
            public void onClick(View v) {
                //显示指定	intent所指的对象是个	service
                Intent intent = new Intent(Main2Activity.this, MyService.class);
                switch(v.getId()){
                    case R.id.startMusic:
                        //开始服务
                        startService(intent);
                        break;
                    case R.id.stopMusic:
                        //停止服务
                        stopService(intent);
                        break;
                    case R.id.bindMusic:
                        //绑定服务
                        bindService(intent, conn, Context.BIND_AUTO_CREATE);
                        break;
                    case R.id.unbindMusic:
                        //解绑服务
                        unbindService(conn);
                        break;
                }
            }
        };

        //绑定点击监听
        btnStart.setOnClickListener(ocl);
        btnStop.setOnClickListener(ocl);
        btnBind.setOnClickListener(ocl);
        btnUnbind.setOnClickListener(ocl);
    }

    //定义服务链接对象
    final ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(Main2Activity.this, "Main2Activity onSeviceDisconnected"
                    , Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Main2Activity onSeviceDisconnected");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(Main2Activity.this, "Main2Activity onServiceConnected"
                    ,Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Main2Activity onServiceConnected");
        }
    };
}

