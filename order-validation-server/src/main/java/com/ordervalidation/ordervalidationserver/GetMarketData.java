package com.ordervalidation.ordervalidationserver;

import com.ordervalidation.ordervalidationserver.marketdata.Trade;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

import java.util.List;

public interface GetMarketData {
    @GET("/md")
    Call<List<Trade>> getMarketData();
}
