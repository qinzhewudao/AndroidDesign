package com.example.hasee.testandroid;

import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;



public class TestJson extends Fragment{
    TextView txtTextView;
    Button btn;
    String result = "";
    String target = "https://api.heweather.com/x3/weather?cityid=CN101010100&key=af71e684a8f540feaaad7c92566324c1";
    URL url;
    private ProgressDialog dialog ;
    Handler handler;

    public TestJson(ProgressDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_test_json, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //添加弹出的对话框
        dialog.setTitle("提示") ;
        dialog.setMessage("正在获取天气，请稍后···") ;
        btn = (Button) view.findViewById(R.id.btn);
        txtTextView = (TextView) view.findViewById(R.id.txt);

        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.show();
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        send();
                        Message message = handler.obtainMessage();
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (result != null) {
                    dialog.dismiss();
                    txtTextView.setText(jiexi());
                }
            }
        };
    }

    public void send() {
        try {
            url = new URL(target);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader buffered = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String input = null;
            while ((input = buffered.readLine()) != null) {
                result += input + "\n";
            }
            buffered.close();
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String jiexi() {
        try {
            JSONArray jsonObjs = new JSONObject(result).getJSONArray("HeWeather data service 3.0");
            JSONObject japi = ((JSONObject) jsonObjs.opt(0))
                    .getJSONObject("aqi");
            JSONObject japi_city = japi.getJSONObject("city");

            //PM2.5

            String pm25 = japi_city.getString("pm25");
            // 空气质量
            String qlty = japi_city.getString("qlty");
            JSONObject jbasic = ((JSONObject) jsonObjs.opt(0))
                    .getJSONObject("basic");

            //城市名称

            String city = jbasic.getString("city");
            JSONArray jdaily_forecast = ((JSONObject) jsonObjs.opt(0))
                    .getJSONArray("daily_forecast");
            JSONObject jdaily_forecast_1 = ((JSONObject) jdaily_forecast.opt(0))
                    .getJSONObject("tmp");
            // 最高温度
            String max = jdaily_forecast_1.getString("max");
            String min = jdaily_forecast_1.getString("min");
            JSONObject jnow = ((JSONObject) jsonObjs.opt(0))
                    .getJSONObject("now");

            //当前温度

            String tmp = jnow.getString("tmp");
            // 湿度
            String hum = jnow.getString("hum");
            //天气描述
            String txtdescrible = jnow.getJSONObject("cond").getString("txt");
            JSONObject jsuggestion = ((JSONObject) jsonObjs.opt(0))
                    .getJSONObject("suggestion");

            //紫外线

            String ultraviolet = jsuggestion.getJSONObject("uv").getString("brf");
            return "城市：" + city + " ，PM2.5:" + pm25 + "，空气质量：" + qlty + " ，当前温度：" + tmp + "，湿度：" + hum
                    + "，天气情况：" + txtdescrible
                    + "，紫外线：" + ultraviolet + "，温度：" + min + "℃~" + max + "℃";

        } catch (JSONException ex) {

            ex.printStackTrace();

        }
        return null;
    }
}
