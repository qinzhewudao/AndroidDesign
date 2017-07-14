package com.example.hasee.testandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.LoginFilter;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.os.CountDownTimer;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.MenuItem;
import android.util.Log;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    AppCompatActivity a = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//设置从那个配置文件读取那个控件ID

        //setContentView(R.layout.login);
        //Log.e("sy", "onCreate: " );

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TextView tv = (TextView) findViewById(R.id.textview02);//通过java改变控件里的属性值
        //tv.setText("hello modify!");
        //tv.setTextColor(Color.BLUE);   //setTextColor必须接受0xFFFFFFFF8位的颜色值或者是Color.RED等
                                       //不然会把text给消除掉
       //TextView redpoint = (TextView) findViewById(R.id.redpoint);
              // redpoint.setVisibility(View.VISIBLE);


        Intent intent = getIntent();
        //从Intent当中根据key取得value
        String account = intent.getStringExtra("account");
        String password = intent.getStringExtra("password");
        //根据控件的ID得到响应的控件对象
        TextView showaccount = (TextView)findViewById(R.id.showaccount);
        TextView showpassword = (TextView)findViewById(R.id.showpassword);
        //为控件设置Text值
        Log.i("sy",account+" "+password);

        showaccount.setText("account:"+account);
        showpassword.setText("password:"+password);

        Button submit= (Button) findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(a,Chat.class);
                startActivity(intent);
                Log.e("sy","error 1");
                finish();
                Log.e("sy","error 2");
            }
        });

        final LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1);
        final TextView text1 = new TextView(this) ;

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


       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    int clickcount = 1;
    public TextView addNewpic() {
        TextView newtext = new TextView(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onDestroy()
    {
        super.onDestroy();//必须要加super调用父类的销毁程序，不然程序会崩溃
        Log.e("sy","i am dead");
    }


}
