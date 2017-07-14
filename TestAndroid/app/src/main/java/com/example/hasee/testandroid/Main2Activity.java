package com.example.hasee.testandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {

    AppCompatActivity a = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View z) {
                Intent intent = new Intent(a,MainActivity.class);
                intent.putExtra("account", account.getText().toString());
                intent.putExtra("password", password.getText().toString());
                startActivity(intent);
                Log.i("sy","i am continue");
                finish();
            }
        });
    }
}
