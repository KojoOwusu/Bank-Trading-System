package com.tradingsystem.reportingservice.webinterfaces;

import com.tradingsystem.reportingservice.OrderStatus;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrderStatusInterface {
    @GET("/api/getorder")
    Call<OrderStatus> getOrderStatus(@Query("ID") String ID, @Query("exchange") String exchange);
}
