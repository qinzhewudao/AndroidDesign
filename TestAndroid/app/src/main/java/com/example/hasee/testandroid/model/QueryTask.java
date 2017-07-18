package com.example.hasee.testandroid.model;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hasee.testandroid.R;

/**
 * Created by hasee on 2017/7/18.
 */

public class QueryTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        return null;
    }

        /*Context context;
        TextView tv_result;

        private static final String JUHE_URL_ENVIRONMENT_AIR_PM =
                "http://web.juhe.cn:8080/environment/air/pm";
        private static final String JUHE_APPKEY = "你申请的APPKEY值";

        public QueryTask(Context context, TextView tv_result) {
            // TODO Auto-generated constructor stub
            super();
            this.context = context;
            this.tv_result = tv_result;
        }

        @Override
        protected String doInBackground(String... params) {
            String city = params[0];

            ArrayList<NameValuePair> headerList = new ArrayList<NameValuePair>();
            headerList.add(new BasicNameValuePair("Content-Type", "text/html; charset=utf-8"));

            String targetUrl = JUHE_URL_ENVIRONMENT_AIR_PM;

            ArrayList<NameValuePair> paramList = new ArrayList<NameValuePair>();
            paramList.add(new BasicNameValuePair("key", JUHE_APPKEY));
            paramList.add(new BasicNameValuePair("dtype", "json"));
            paramList.add(new BasicNameValuePair("city", city));

            for (int i = 0; i < paramList.size(); i++) {
                NameValuePair nowPair = paramList.get(i);
                String value = nowPair.getValue();
                try {
                    value = URLEncoder.encode(value, "UTF-8");
                } catch (Exception e) {
                }
                if (i == 0) {
                    targetUrl += ("?" + nowPair.getName() + "=" + value);
                } else {
                    targetUrl += ("&" + nowPair.getName() + "=" + value);
                }
            }

            HttpGet httpRequest = new HttpGet(targetUrl);
            try {
                for (int i = 0; i < headerList.size(); i++) {
                    httpRequest.addHeader(headerList.get(i).getName(),
                            headerList.get(i).getValue());
                }

                HttpClient httpClient = new DefaultHttpClient();

                HttpResponse httpResponse = httpClient.execute(httpRequest);
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    String strResult = EntityUtils.toString(httpResponse.getEntity());
                    return strResult;
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    int resultCode = jsonObject.getInt("resultcode");
                    if (resultCode == 200) {
                        JSONArray resultJsonArray = jsonObject.getJSONArray("result");
                        JSONObject resultJsonObject = resultJsonArray.getJSONObject(0);
                        String output = context.getString(R.string.city) + ": " + resultJsonObject.getString("city") + "\n"
                                + context.getString(R.string.PM25) + ": " + resultJsonObject.getString("PM2.5") + "\n"
                                + context.getString(R.string.AQI) + ": " + resultJsonObject.getString("AQI") + "\n"
                                + context.getString(R.string.quality) + ": " + resultJsonObject.getString("quality") + "\n"
                                + context.getString(R.string.PM10) + ": " + resultJsonObject.getString("PM10") + "\n"
                                + context.getString(R.string.CO) + ": " + resultJsonObject.getString("CO") + "\n"
                                + context.getString(R.string.NO2) + ": " + resultJsonObject.getString("NO2") + "\n"
                                + context.getString(R.string.O3) + ": " + resultJsonObject.getString("O3") + "\n"
                                + context.getString(R.string.SO2) + ": " + resultJsonObject.getString("SO2") + "\n"
                                + context.getString(R.string.time) + ": " + resultJsonObject.getString("time") + "\n";
                        tv_result.setText(output);
                    } else if (resultCode == 202) {
                        String reason = jsonObject.getString("reason");
                        tv_result.setText(reason);
                    } else {
                        Toast.makeText(context, "查询失败",
                                Toast.LENGTH_LONG).show();
                        tv_result.setText("");
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(context, "查询失败",
                        Toast.LENGTH_LONG).show();
                tv_result.setText("");
            }
        }*/
}
