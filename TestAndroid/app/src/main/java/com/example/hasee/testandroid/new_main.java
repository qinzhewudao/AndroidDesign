package com.example.hasee.testandroid;


import android.app.ProgressDialog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hasee.testandroid.adapter.FragAdapter;

@SuppressLint("NewApi")
public class new_main extends FragmentActivity implements OnClickListener {

    ProgressDialog dialog;
    FragmentActivity a = this;
    private Button button1,button2,button3;
    private List<Button> btnList = new ArrayList<>();
    private FragmentManager fm;
    private FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_viewpage);

        try{
            dialog = new ProgressDialog(this);

            //构造适配器
            List<Fragment> fragments=new ArrayList<Fragment>();
            fragments.add(new MainActivity(a));
            fragments.add(new TestAsync(dialog));
            fragments.add(new TestJson(dialog));
            FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);

            //设定适配器
            ViewPager vp = (ViewPager)findViewById(R.id.viewpager);
            vp.setAdapter(adapter);
//            findById();
//            fm = getFragmentManager();
//            ft = fm.beginTransaction();
//
//            setBackgroundColorById(R.id.movie_btn);
//            final FragmentTransaction replace = ft.replace(R.id.fragment_content, new MainActivity(a));
//            ft.commit();
        }catch(Exception e)
        {
            Log.e("sy","一开始选择fragement就错了"+e);
        }

    }

    @Override
    public void onClick(View v) {

    }

//    private void findById() {
//        button1 = (Button)this.findViewById(R.id.movie_btn);
//        button2 = (Button)this.findViewById(R.id.tv_btn);
//        button3 = (Button) this.findViewById(R.id.anime_btn);
//
//        button1.setOnClickListener(this);
//        button2.setOnClickListener(this);
//        button3.setOnClickListener(this);
//
//
//        btnList.add(button1);
//        btnList.add(button2);
//        btnList.add(button3);
//        }
//
//    private void setBackgroundColorById(int btnId) {
//            for (Button btn : btnList) {
//                if (btn.getId() == btnId){
//                    btn.setBackgroundColor(Color.GREEN);
//                    }else {
//                    btn.setBackgroundColor(Color.BLUE);
//                }
//            }
//    }
//
//
//
//    @Override
//    public void onClick(View v) {
//
//            fm = getFragmentManager();
//            ft = fm.beginTransaction();
//            switch (v.getId()) {
//
//            case R.id.movie_btn:
//            setBackgroundColorById(R.id.movie_btn);
//
//            ft.replace(R.id.fragment_content,new MainActivity(a));
//            break;
//
//            case R.id.tv_btn:
//            setBackgroundColorById(R.id.tv_btn);
//
//            ft.replace(R.id.fragment_content,new TestAsync(dialog));
//            break;
//
//            case R.id.anime_btn:
//            setBackgroundColorById(R.id.anime_btn);
//
//            ft.replace(R.id.fragment_content,new TestJson(dialog));
//            break;
//
//    default:
//            break;
//            }
//            // 不要忘记提交
//            ft.commit();
//            }
//
//    private long exitTime = 0;
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
//            if((System.currentTimeMillis()-exitTime) > 2000){
//                Toast.makeText(a.getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
//                exitTime = System.currentTimeMillis();
//            } else {
//                finish();
//                System.exit(0);
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

}