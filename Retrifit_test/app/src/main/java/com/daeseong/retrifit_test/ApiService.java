package com.daeseong.retrifit_test;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("public/ticker/BTC")
    Call<tickerBTC> getTickerBTC();
}
