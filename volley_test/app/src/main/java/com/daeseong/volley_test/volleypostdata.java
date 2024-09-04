package com.daeseong.volley_test;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class volleypostdata {

    public static void getPostData(String sUrl, Context context, ProgressDialog progressDialog, TextView tv2, final String... params) {

        StringRequest sr = new StringRequest(Request.Method.POST, sUrl, new volleypostListener(context, progressDialog, tv2), new volleypostErrorListener(context, progressDialog)) {

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
        sr.setTag("getPostData");
        MyApplication.getRequestQueue().add(sr);
    }
}


class volleypostListener implements Response.Listener<String>{

    private  static final String TAG = volleyListener.class.getSimpleName();

    private Context context;
    private ProgressDialog progressDialog;
    private TextView tv2;

    public volleypostListener(Context context, ProgressDialog progressDialog, TextView tv2) {
        this.context = context;
        this.progressDialog = progressDialog;
        this.tv2 = tv2;
    }

    @Override
    public void onResponse(String response) {

        //Log.e(TAG, "volleypostListener onResponse: " + response);

        tv2.setText(response);

        int nTop = tv2.getLayout().getLineTop(tv2.getLineCount());
        int nScrollY = nTop - tv2.getHeight();
        if(nScrollY > 0){
            tv2.scrollTo(0, nScrollY);
        }else {
            tv2.scrollTo(0, 0);
        }

        progressDialog.dismiss();
        //context.startActivity(new Intent(context, MainActivity.class));
        //((Activity) context).finish();
    }
}

class volleypostErrorListener implements Response.ErrorListener {

    private  static final String TAG = volleyErrorListener.class.getSimpleName();

    private Context context;
    private ProgressDialog progressDialog;

    public volleypostErrorListener(Context context, ProgressDialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Log.e(TAG, "volleypostErrorListener onErrorResponse: " + error.getMessage().toString());

        progressDialog.dismiss();
    }
}