package com.daeseong.okhttpclient_test.common;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtilOK {

    private static final String TAG = HttpUtilOK.class.getSimpleName();

    public static void getBTC(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void getlogin(String address, String ID, String PWD, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("enctp", "1")
                .add("encpw" , "0")
                .add("encnm", "0")
                .add("svctype", "0")
                .add("svc" , "0")
                .add("viewtype" , "0")
                .add("postDataKey" , "0")
                .add("smart_LEVEL", "-1")
                .add("logintp" , "0")
                .add("localechange" , "0")
                .add("theme_mode" , "0")
                .add("ls" , "0")
                .add("pre_id" , "0")
                .add("resp" , "0")
                .add("exp" , "0")
                .add("ru" , "0")
                .add("nvlong" , "on")
                .add("id" , ID)
                .add("pw", PWD)
                .build();

        Request request = new Request.Builder()
                .url(address)
                .header("Referer" , "https://nid.naver.com/nidlogin.login")
                .header("Content-Type" , "application/x-www-form-urlencoded")
                .header("User-Agent" , "Mozilla/5.0")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
