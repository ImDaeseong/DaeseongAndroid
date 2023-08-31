package com.daeseong.retrifit_test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TickerApiClient {

    private static final String BASE_URL = "https://api.bithumb.com/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static ApiService apiService = retrofit.create(ApiService.class);

    public static void getTickerBTC(Callback<tickerBTC> callback) {
        Call<tickerBTC> call = apiService.getTickerBTC();
        call.enqueue(callback);
    }
}
