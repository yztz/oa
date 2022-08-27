package com.oasystem.service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oasystem.MyCallback;
import com.oasystem.R;
import com.oasystem.utils.ReadTools;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WebService {
    private static String TAG = "web";

    private static OkHttpClient client;

    static {
        client = new OkHttpClient();
    }


    public static void asyncGet(Request request, MyCallback onSuccess, MyCallback onFailure) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure: fail");
                if (onFailure != null) {

                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response);
                if (response.isSuccessful()) {
                    JSONObject obj = JSONObject.parseObject(response.body().string());
                    Log.d(TAG, "onResponse: " + obj);
                    if (onSuccess != null) {
                        onSuccess.callback(obj);
                    }
                }
            }
        });
    }

    private static MyCallback dflFailureCallback = data -> {
        Log.d(TAG, "failure: req fail but ignored");
    };

    public static void asyncGet(Request request, MyCallback onSuccess) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                JSONObject data = new JSONObject();
                data.put("call", call);
                data.put("exception", e);
                dflFailureCallback.callback(data);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG, "onResponse: " + response);
                if (response.isSuccessful()) {
                    JSONObject obj = JSONObject.parseObject(response.body().string());
                    Log.d(TAG, "onResponse: " + obj);
                    if (onSuccess != null) {
                        onSuccess.callback(obj);
                    }
                }
            }
        });
    }


}
