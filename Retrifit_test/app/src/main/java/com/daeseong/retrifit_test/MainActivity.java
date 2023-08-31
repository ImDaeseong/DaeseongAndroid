package com.daeseong.retrifit_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TickerApiClient.getTickerBTC(new Callback<tickerBTC>() {
                    @Override
                    public void onResponse(Call<tickerBTC> call, Response<tickerBTC> response) {

                        if (response.isSuccessful()) {

                            tickerBTC ticker = response.body();
                            if (ticker != null) {

                                String openingPrice = ticker.getData().getOpeningPrice();
                                String closingPrice = ticker.getData().getClosingPrice();
                                String minPrice = ticker.getData().getMinPrice();
                                String maxPrice = ticker.getData().getMaxPrice();
                                String unitsTraded = ticker.getData().getUnitsTraded();
                                String accTradeValue = ticker.getData().getAccTradeValue();
                                String prevClosingPrice = ticker.getData().getPrevClosingPrice();
                                String unitsTraded24H = ticker.getData().getUnitsTraded24H();
                                String accTradeValue24H = ticker.getData().getAccTradeValue24H();
                                String fluctate24H = ticker.getData().getFluctate24H();
                                String fluctateRate24H = ticker.getData().getFluctateRate24H();
                                String date = ticker.getData().getDate();
                                Log.e(TAG, openingPrice);
                                Log.e(TAG, closingPrice);
                                Log.e(TAG, minPrice);
                                Log.e(TAG, maxPrice);
                                Log.e(TAG, unitsTraded);
                                Log.e(TAG, accTradeValue);
                                Log.e(TAG, prevClosingPrice);
                                Log.e(TAG, unitsTraded24H);
                                Log.e(TAG, accTradeValue24H);
                                Log.e(TAG, fluctate24H);
                                Log.e(TAG, fluctateRate24H);
                                Log.e(TAG, date);
                            }

                        } else {
                            Log.e(TAG, String.valueOf(response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<tickerBTC> call, Throwable t) {

                        Log.e(TAG, t.getMessage());
                    }
                });

            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}