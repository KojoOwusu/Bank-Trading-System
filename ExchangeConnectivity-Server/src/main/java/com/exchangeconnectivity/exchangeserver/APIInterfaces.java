package com.exchangeconnectivity.exchangeserver;
import com.exchangeconnectivity.exchangeserver.resourceclasses.Order;
import retrofit2.Call;
import retrofit2.http.*;


public class APIInterfaces {
    private static final String apikey = "8ec70649-0be5-4b5a-9e29-60bc424d7b60";

    public static interface CreateOrderService {
        @POST("8ec70649-0be5-4b5a-9e29-60bc424d7b60/order")
        Call<String> createOrder(@Body Order newOrder);
    }

    public static interface CancelOrder {
        @DELETE("8ec70649-0be5-4b5a-9e29-60bc424d7b60/order/{orderID}")
        Call<String> cancelOrder(@Path("orderID") String orderId);
    }

    public static interface EditOrderService {
        @PUT("8ec70649-0be5-4b5a-9e29-60bc424d7b60/order/{orderID}")
        Call<String> editOrder(@Body Order order, @Path("orderID") String orderId);
    }

}
