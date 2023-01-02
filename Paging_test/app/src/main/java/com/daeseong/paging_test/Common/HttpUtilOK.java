package com.daeseong.paging_test.Common;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtilOK {

    private static final String TAG = HttpUtilOK.class.getSimpleName();

    public static void getData(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}