package com.daeseong.volley_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class MainActivity3 extends AppCompatActivity {

    private  static final String TAG = MainActivity3.class.getSimpleName();

    private Button button1;
    private TextView tv1, tv2;

    private RequestQueue requestQueue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        initVolley();

        tv1 = (TextView)findViewById(R.id.tv1);
        tv1.setMovementMethod(new ScrollingMovementMethod());

        tv2 = (TextView)findViewById(R.id.tv2);
        tv2.setMovementMethod(new ScrollingMovementMethod());

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get
                requestQueue.add(requestGetJson());
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

    private JsonObjectRequest requestGetJson(){

        String sUrl = "https://api.bithumb.com/public/ticker/BTC";

        final JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, sUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    tv1.setText(response.toString());

                    String sStatus = response.getString("status");
                    if(sStatus.equals("0000")){

                        JSONObject data = response.getJSONObject("data");
                        String opening_price = data.getString("opening_price");
                        String closing_price = data.getString("closing_price");
                        String min_price = data.getString("min_price");
                        String max_price = data.getString("max_price");
                        String units_traded = data.getString("units_traded");
                        String acc_trade_value = data.getString("acc_trade_value");
                        String prev_closing_price = data.getString("prev_closing_price");
                        String units_traded_24H = data.getString("units_traded_24H");
                        String acc_trade_value_24H = data.getString("acc_trade_value_24H");
                        String fluctate_24H = data.getString("fluctate_24H");
                        String fluctate_rate_24H = data.getString("fluctate_rate_24H");
                        String date = data.getString("date");

                        StringBuilder stringBuilder = new StringBuilder();

                        stringBuilder.append(opening_price);
                        stringBuilder.append("\n");

                        stringBuilder.append(closing_price);
                        stringBuilder.append("\n");

                        stringBuilder.append(min_price);
                        stringBuilder.append("\n");

                        stringBuilder.append(max_price);
                        stringBuilder.append("\n");

                        stringBuilder.append(units_traded);
                        stringBuilder.append("\n");

                        stringBuilder.append(acc_trade_value);
                        stringBuilder.append("\n");

                        stringBuilder.append(prev_closing_price);
                        stringBuilder.append("\n");

                        stringBuilder.append(units_traded_24H);
                        stringBuilder.append("\n");

                        stringBuilder.append(acc_trade_value_24H);
                        stringBuilder.append("\n");

                        stringBuilder.append(fluctate_24H);
                        stringBuilder.append("\n");

                        stringBuilder.append(fluctate_rate_24H);
                        stringBuilder.append("\n");

                        stringBuilder.append(date);

                        tv2.setText(stringBuilder.toString());
                    }

                }catch (Exception ex){
                    Log.e(TAG, "onResponse Exception: " + ex.getMessage().toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e(TAG, "requestGetData onErrorResponse: " + error.getMessage().toString());
            }
        });

        jr.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jr.setShouldCache(false);
        return jr;
    }
}