package com.example.hasee.testandroid;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasee.testandroid.adapter.ContactAdapter;
import com.example.hasee.testandroid.model.ContactModel;
import android.support.v4.app.Fragment;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Fragment {

    FragmentActivity a;
    Myhandler myhandler;

    public MainActivity(FragmentActivity a) {
        this.a = a;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void showNotificationWithAction(){
        NotificationManager notifyManager=(NotificationManager )a.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent=new Intent(a,Main2Activity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(a,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder=new Notification.Builder(a)
                .setContentTitle("测试标题")//设置通知栏标题
                .setContentText("测试内容") //设置通知栏显示内容
                .setTicker("测试通知来啦") //通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setAutoCancel(true)//设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                //Notification.DEFAULT_ALL
                //Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                .setSmallIcon(R.drawable.man1);//设置通知小ICON
        notifyManager.notify(2,builder.build());
        builder.setContentIntent(pendingIntent);
        Notification notify=builder.build();
        notify.defaults=Notification.DEFAULT_SOUND;
        notify.flags|=Notification.FLAG_SHOW_LIGHTS;
        notify.flags|=Notification.FLAG_AUTO_CANCEL;
        //MainActivity.notify(1,notifyManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setContentView(R.layout.login);
        //Log.e("sy", "onCreate: " );


        //TextView tv = (TextView) findViewById(R.id.textview02);//通过java改变控件里的属性值
        //tv.setText("hello modify!");
        //tv.setTextColor(Color.BLUE);   //setTextColor必须接受0xFFFFFFFF8位的颜色值或者是Color.RED等
        //不然会把text给消除掉
        //TextView redpoint = (TextView) findViewById(R.id.redpoint);
        // redpoint.setVisibility(View.VISIBLE);


        Intent intent = a.getIntent();
        //从Intent当中根据key取得value
        String account = intent.getStringExtra("account");
        String password = intent.getStringExtra("password");
        //根据控件的ID得到响应的控件对象
        TextView showaccount = (TextView) view.findViewById(R.id.showaccount);
        //为控件设置Text值
        Log.i("sy",account+" "+password);

        showaccount.setText("account:"+account);

        Button submit= (Button) view.findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(a,Chat.class);
                startActivity(intent);
                Log.e("sy","error 1");
                a.finish();
                Log.e("sy","error 2");
            }
        });

        final LinearLayout layout1 = (LinearLayout) view.findViewById(R.id.layout1);
        final TextView text1 = new TextView(a) ;

        Resources resources = MainActivity.this.getResources();
        final Drawable btnDrawable = resources.getDrawable(R.drawable.back_ground);
        text1.setBackgroundDrawable(btnDrawable);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100, 100);
        lp.gravity = Gravity.CENTER_VERTICAL;
        text1.setGravity(Gravity.CENTER_HORIZONTAL);
        text1.setLayoutParams(lp);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("sy","start click");

                layout1.addView(addNewpic());
            }
        });
        layout1.addView(text1);    //动态添加各种控件


        //listview
        ListView mListView   =   (ListView) view.findViewById(R.id.lvcontact);

        Bitmap bitmap = getHttpBitmap("http://pic.qqtn.com/up/2017-6/2017062711010343002.jpg");
        Bitmap bitmap1 = getHttpBitmap("http://pic.qqtn.com/up/2017-6/2017062711010343002.jpg");
        ContactModel bean1   =   new ContactModel();     //创建ContactModel对象
        bean1.setMsgcount("7");          //设置type类型
        bean1.setContactimage(bitmap);        //设置图片
        bean1.setContent("hello peter");                    //设置文本
        //以下同

        ContactModel bean2   =   new ContactModel();     //创建ContactModel对象
        bean2.setMsgcount("3");          //设置type类型
        bean2.setContactimage(bitmap1);        //设置图片
        bean2.setContent("hello petersburg");                    //设置文本

        ContactModel bean3   =   new ContactModel();     //创建ContactModel对象
        bean3.setMsgcount("5");          //设置type类型
        bean3.setContactimage(bitmap1);        //设置图片
        bean3.setContent("hello peter's");                    //设置文本

        //创建ArrayList<ContactModel>类型的data
        List<ContactModel> data = new ArrayList<>();
        //添加数据，类型为ContactModel
        data.add(bean1);
        data.add(bean2);
        data.add(bean3);
        //为ListView设置适配器
        mListView.setAdapter(new ContactAdapter(a , data));


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        myhandler = new Myhandler();
        final MyTHread myTHread = new MyTHread();

        Button testhandler = (Button) view.findViewById(R.id.testhanlder);

        testhandler.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 启动一个子线程
                showNotificationWithAction();
                new Thread(myTHread).start();  //一定要在这里面启动！
            }
        });

    }


    public class Myhandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Button testhandler = (Button) a.findViewById(R.id.testhanlder);
            testhandler.setTextColor(0xFFFFFFFF);
        }
    }

    public class MyTHread implements Runnable{
        @Override
        public void run() {
            try{
                Button testhandler = (Button) a.findViewById(R.id.testhanlder);
                testhandler.setTextColor(0xFFFFFFFF);
            }catch (Exception e)
            {
                Log.e("sy","其余线程修改UI线程的view"+e);
            }

            Message msg = new Message();

            MainActivity.this.myhandler.sendMessage(msg);

        }
    }

    public static Bitmap getHttpBitmap(String url)
    {
        URL myFileURL;
        Bitmap bitmap=null;
        try{
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        }catch(Exception e){
            Log.e("sy","bitmap"+e);
        }

        return bitmap;

    }

    int clickcount = 1;
    public TextView addNewpic() {
        TextView newtext = new TextView(a);
        newtext.setBackgroundColor(Color.RED);
        Resources resources = MainActivity.this.getResources();
        Drawable btnDrawable = resources.getDrawable(R.drawable.design_red_point);
        newtext.setBackgroundDrawable(btnDrawable);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100, 100);
        lp.gravity = Gravity.CENTER_VERTICAL;
        newtext.setLayoutParams(lp);
        newtext.setText(Integer.toString(clickcount));
        newtext.setGravity(Gravity.CENTER);
        newtext.setTextSize(20);
        clickcount++;
        return newtext;


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        a.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //监听back键
   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            // 创建退出对话框
            final AlertDialog isExit = new AlertDialog.Builder(this).create();
            // 设置对话框标题
            isExit.setTitle("系统提示");
            // 设置对话框消息
            isExit.setMessage("确定要退出吗");
            // 添加选择按钮并注册监听
            isExit.setButton("确定", listener);
            isExit.setButton2("取消", listener);
            // 显示对话框
            isExit.show();

            //计时器的实现
            Handler handler = new Handler();
            handler.postDelayed(new Runnable()
            {
                public void run()
                {
                    if(isExit.isShowing())
                        isExit.dismiss();
                }
            },3000);
        }
        return false;

    }*/
    /**监听对话框里面的button点击事件*/
    /*DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
    {
        private CountDownTimer countDownTimer;

        public void onClick(DialogInterface dialog, int which)
        {
            switch (which)
            {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    finish();
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };*/


}
