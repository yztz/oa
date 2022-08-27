package com.oasystem.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestUtils {
    private static RequestBody makeJson(JSONObject jsonObject) {
        return RequestBody.Companion.create(jsonObject.toJSONString(),
                        MediaType.parse("application/json; charset=utf-8"));
//        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"),map.toString())
    }

    public static Request buildPostRequest(String url, JSONObject body) {
        return new Request.Builder()
                .addHeader("X-Token", UserUtils.getToken())
                .post(makeJson(body))
                .url(url)
                .build();
    }

    public static Request buildGetRequest(String url, JSONObject params) {
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();

        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                builder.addQueryParameter(key, params.get(key).toString());
            }
        }
        String newUrl = builder.build().toString();

        return new Request.Builder()
                .addHeader("X-Token", UserUtils.getToken())
                .get()
                .url(newUrl)
                .build();
    }


}
