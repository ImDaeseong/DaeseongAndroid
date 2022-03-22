package com.daeseong.volley_test;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class volleygetdata {

    public static void getData(String sUrl, Context context, ProgressDialog progressDialog, TextView tv1) {

        StringRequest sr = new StringRequest(Request.Method.GET, sUrl, new volleyListener(context, progressDialog, tv1), new volleyErrorListener(context, progressDialog));
        sr.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        sr.setShouldCache(false);
        sr.setTag("getData");
        MyApplication.getRequestQueue().add(sr);
    }
}

class volleyListener implements Response.Listener<String>{

    private  static final String TAG = volleyListener.class.getSimpleName();

    private Context context;
    private ProgressDialog progressDialog;
    private TextView tv1;

    public volleyListener(Context context, ProgressDialog progressDialog, TextView tv1) {
        this.context = context;
        this.progressDialog = progressDialog;
        this.tv1 = tv1;
    }

    @Override
    public void onResponse(String response) {

        //Log.e(TAG, "volleyListener onResponse: " + response);

        tv1.setText(response);

        int nTop = tv1.getLayout().getLineTop(tv1.getLineCount());
        int nScrollY = nTop - tv1.getHeight();
        if(nScrollY > 0){
            tv1.scrollTo(0, nScrollY);
        }else {
            tv1.scrollTo(0, 0);
        }

        progressDialog.dismiss();
        //context.startActivity(new Intent(context, MainActivity.class));
        //((Activity) context).finish();
    }
}

class volleyErrorListener implements Response.ErrorListener {

    private  static final String TAG = volleyErrorListener.class.getSimpleName();

    private Context context;
    private ProgressDialog progressDialog;

    public volleyErrorListener(Context context, ProgressDialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Log.e(TAG, "volleyErrorListener onErrorResponse: " + error.getMessage().toString());

        progressDialog.dismiss();
    }
}