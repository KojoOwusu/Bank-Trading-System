package com.ordervalidation.ordervalidationserver;
import com.google.gson.GsonBuilder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import turntabl.trading.ordervalidservice.ValidateOrder;
import turntabl.trading.ordervalidservice.ValidationResponse;

import java.io.IOException;

@Endpoint
public class ServiceEndpoint {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://1435113c71c9.ngrok.io").addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create())).build();

    private static final String NAMESPACE_URI = "http://turntabl/trading/ordervalidservice";


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "validateOrder")
    @ResponsePayload
    public ValidationResponse validateOrder(@RequestPayload ValidateOrder request){
        if(validate(request)){
            String orderID = sendOrderRequest(request);  //make request to Trade engine sending body {id, name, quantity, price, side}
            ValidationResponse response = new ValidationResponse();
            response.setOrderID(orderID);
            response.setOrderstatus("Order validated");
            return response;
        }
            ValidationResponse response = new ValidationResponse();
            response.setOrderID("");
            response.setOrderstatus("Order failed validation");
            return response;
    }

    public String sendOrderRequest(ValidateOrder order){
        PostOrderService.orderService service = retrofit.create(PostOrderService.orderService.class);
        Call<String> req = service.sendOrderRequest(order);
        try {
            return req.execute().body();
        }catch (IOException e) {
            e.printStackTrace();
            return "order failed";
        }
    }

    public Boolean validate(ValidateOrder request){return true; }  //this function should handle validation of order before even placing it.
}


