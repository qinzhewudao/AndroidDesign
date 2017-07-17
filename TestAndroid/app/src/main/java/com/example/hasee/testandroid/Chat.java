package com.example.hasee.testandroid;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import com.example.hasee.testandroid.TestData;
import com.example.hasee.testandroid.adapter.ChatAdapter;
import com.example.hasee.testandroid.model.ChatModel;
import com.example.hasee.testandroid.model.ItemModel;
import com.example.hasee.testandroid.receiver.MyReceiver;
import com.example.hasee.testandroid.SQLite.ChatHelper;
import java.util.ArrayList;

public class Chat extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private EditText et;
    AppCompatActivity a= this;
    private TextView tvSend;
    private TextView tvReceive;
    private TextView chatreturn;
    private TextView testbroadcast;
    private String content;
    private ChatHelper chatsql;
    MyReceiver receiver = new MyReceiver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.e("sy","error 3");
            setContentView(R.layout.chat);

            recyclerView = (RecyclerView) findViewById(R.id.recylerView);
            et = (EditText) findViewById(R.id.et);
            tvSend = (TextView) findViewById(R.id.tvSend);
            tvReceive = (TextView) findViewById(R.id.tvReceive);
            chatreturn = (TextView) findViewById(R.id.chatreturn);
            testbroadcast = (TextView) findViewById(R.id.testbroadcast);
            chatsql = new ChatHelper(this);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter = new ChatAdapter());
            try{
                adapter.replaceAll(TestData.getTestAdData(chatsql.query()));
            }catch(Exception e)
            {
                Log.e("sy","加载不了model"+e);
            }

            initData();

            IntentFilter filter = new IntentFilter();
            filter.addAction("TestBroadCast");

            registerReceiver(receiver, filter);
            testbroadcast.setOnClickListener(listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        //应用的最后一个Activity关闭时应释放DB
        chatsql.close();
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        Log.e("sy","error 4");
    }

    private void initData() {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                content = s.toString().trim();
            }
        });

        tvSend.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ArrayList<ItemModel> data = new ArrayList<>();
                ChatModel model = new ChatModel();
                model.setIcon("http://pic.qqtn.com/up/2017-6/2017062711010343002.jpg");
                model.setContent(content);
                model.setType("CHAT_A");
                chatsql.insert("http://pic.qqtn.com/up/2017-6/2017062711010343002.jpg",content,"CHAT_B");
                data.add(new ItemModel(ItemModel.CHAT_B, model));
                adapter.addAll(data);
                et.setText("");
                hideKeyBorad(et);
            }
        });
        tvReceive.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ArrayList<ItemModel> data = new ArrayList<>();
                ChatModel model = new ChatModel();
                model.setIcon("http://pic.qqtn.com/up/2017-6/2017062711010489403.jpg");
                model.setContent(content);
                model.setType("CHAT_A");
                chatsql.insert("http://pic.qqtn.com/up/2017-6/2017062711010489403.jpg",content,"CHAT_A");

                data.add(new ItemModel(ItemModel.CHAT_A, model));
                adapter.addAll(data);
                et.setText("");
                hideKeyBorad(et);
            }
        });
        chatreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(a,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent("TestBroadCast");
            intent.putExtra("msg", "hello receiver");
            Chat.this.sendBroadcast(intent);
            Log.e("sy","women fas ong");
            Toast.makeText(getApplicationContext(), "发送广播成功", Toast.LENGTH_SHORT).show();
        }
    };
    private void hideKeyBorad(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

}
