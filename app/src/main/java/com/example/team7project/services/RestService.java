package com.example.team7project.services;

import com.example.team7project.store.ApplicationCookieJar;

import okhttp3.Callback;
import okhttp3.CookieJar;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RestService {
    private static RestService instance;
    private static final String TAG = "REST";
    private static String baseLocation = "http://10.0.2.2:3000/";
    private OkHttpClient client;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private RestService() {
        CookieJar cookieJar = new ApplicationCookieJar();
        client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();
    }

    public static RestService getInstance() {
        if (instance == null) {
            instance = new RestService();
        }
        return instance;
    }

    public void post(String json, String url, Callback requestCallback) {
        RequestBody requestBody = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(baseLocation + url)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(requestCallback);
    }

    public void get(String url, Callback requestCallback) {
        Request request = new Request.Builder()
                .url(baseLocation + url)
                .build();

        client.newCall(request).enqueue(requestCallback);
    }
}
