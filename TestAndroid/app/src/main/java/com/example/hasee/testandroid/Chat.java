package com.example.hasee.testandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import com.example.hasee.testandroid.TestData;
import com.example.hasee.testandroid.adapter.ChatAdapter;
import com.example.hasee.testandroid.model.ChatModel;
import com.example.hasee.testandroid.model.ItemModel;

import java.util.ArrayList;

public class Chat extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private EditText et;
    private TextView tvSend;
    private TextView tvReceive;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.e("sy","error 3");
            setContentView(R.layout.chat);

            recyclerView = (RecyclerView) findViewById(R.id.recylerView);
            et = (EditText) findViewById(R.id.et);
            tvSend = (TextView) findViewById(R.id.tvSend);
            tvReceive = (TextView) findViewById(R.id.tvReceive);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter = new ChatAdapter());
            adapter.replaceAll(TestData.getTestAdData());
            initData();
    }
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
                model.setIcon("http://img.my.csdn.net/uploads/201508/05/1438760758_6667.jpg");
                model.setContent(content);
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
                model.setIcon("http://img.my.csdn.net/uploads/201508/05/1438760758_6667.jpg");
                model.setContent(content);
                data.add(new ItemModel(ItemModel.CHAT_A, model));
                adapter.addAll(data);
                et.setText("");
                hideKeyBorad(et);
            }
        });

    }
    private void hideKeyBorad(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }
}
