package com.daeseong.volley_test;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class GetRequestData {

    private RequestQueue requestQueue;
    private Context context;

    private static GetRequestData instance;

    public static GetRequestData getInstance(Context context){
        if( instance == null){
            instance = new GetRequestData(context);
        }
        return instance;
    }

    public GetRequestData(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        this.context = context;
    }

    public void clear() {

        if(requestQueue != null){
            requestQueue.cancelAll(this.context);
        }
    }

    public void getData(String sUrl, Response.Listener<String> listener, Response.ErrorListener errorListener, final String... params) {

        StringRequest sr = new StringRequest(Request.Method.GET, sUrl, listener, errorListener);
        sr.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        sr.setShouldCache(false);
        requestQueue.add(sr);
    }

    public void getPostData(String sUrl, Response.Listener<String> listener, Response.ErrorListener errorListener, final String... params) {

        StringRequest sr = new StringRequest(Request.Method.POST, sUrl, listener, errorListener) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> param = new HashMap<>();
                param.put("dobType", params[0]);
                param.put("dsptcKsco", params[1]);
                param.put("continent", params[2]);
                param.put("showItemListCount", params[3]);
                param.put("sepmt61", params[4]);
                return param;
            }
        };

        sr.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        sr.setShouldCache(false);
        requestQueue.add(sr);
    }
}
