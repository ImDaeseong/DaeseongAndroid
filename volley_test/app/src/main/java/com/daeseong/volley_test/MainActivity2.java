package com.daeseong.volley_test;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity2 extends AppCompatActivity {

    private  static final String TAG = MainActivity2.class.getSimpleName();

    private Button button1;
    private ImageView iv1;

    private RequestQueue requestQueue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initVolley();

        iv1 = (ImageView)findViewById(R.id.iv1);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                requestQueue.add(requestImage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try{

            if(requestQueue != null){
                requestQueue.cancelAll(this);;
            }
        }catch (Exception e){
            Log.e(TAG, e.getMessage().toString());
        }
    }

    private void initVolley(){

        requestQueue = Volley.newRequestQueue(this);
    }

    private ImageRequest requestImage(){

        String sUrl = "https://cdn.pixabay.com/photo/2015/07/14/18/14/school-845196_960_720.png";
        int nMaxWidth = 0;
        int nMaxHeight = 0;

        final ImageRequest ir = new ImageRequest(sUrl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                iv1.setImageBitmap(response);
            }
        }, nMaxWidth, nMaxHeight, ImageView.ScaleType.CENTER, Bitmap.Config.ALPHA_8, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e(TAG, "requestImage onErrorResponse: " + error.getMessage().toString());
            }
        });

        ir.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        ir.setShouldCache(false);
        return ir;
    }
}