package com.ordervalidation.ordervalidationserver;
import retrofit2.http.Body;
import turntabl.trading.ordervalidservice.ValidateOrder;
import retrofit2.Call;
import retrofit2.http.POST;
import turntabl.trading.ordervalidservice.ValidationResponse;

public class PostOrderService {
    public static interface orderService {
        @POST("/api/trade")
        Call<String> sendOrderRequest(@Body ValidateOrder order);
    }
}
