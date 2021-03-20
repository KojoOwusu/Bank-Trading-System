package com.ordervalidation.ordervalidationserver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.ordervalidation.ordervalidationserver.jedisconfig.JedisConfig;
import com.ordervalidation.ordervalidationserver.marketdata.OrderResponse;
import com.ordervalidation.ordervalidationserver.marketdata.Trade;
import com.thoughtworks.qdox.model.expression.Or;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import redis.clients.jedis.Jedis;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import turntabl.trading.ordervalidservice.ValidateOrder;
import turntabl.trading.ordervalidservice.ValidationResponse;

import java.io.IOException;
import java.util.List;

@Endpoint
public class ServiceEndpoint {
    private Retrofit retrofit = new Retrofit.Builder().baseUrl("https://trade-services.herokuapp.com").addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create())).build();
   // private Jedis jedis = JedisConfig.createJedisClient();
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String NAMESPACE_URI = "http://turntabl/trading/ordervalidservice";


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "validateOrder")
    @ResponsePayload
    public ValidationResponse validateOrder(@RequestPayload ValidateOrder request){
        try {
            if (validate(request)) {
                OrderResponse orderResponse = sendOrderRequest(request);
                if (orderResponse.getOrderID().isEmpty()){
                    ValidationResponse response = new ValidationResponse();
                    response.setOrderstatus("failed to create order");
                    response.setSide(request.getSide());
                    response.setQuantity(request.getQuantity());
                    response.setProduct(request.getProduct());
                    response.setPrice(request.getPrice());
                    response.setExchange(orderResponse.getExchange());
                    return response;

                }

                ValidationResponse response = new ValidationResponse();
                response.setOrderstatus("Order validated");
                response.setOrderID(orderResponse.getOrderID());
                response.setSide(request.getSide());
               response.setQuantity(request.getQuantity());
                response.setProduct(request.getProduct());
                response.setPrice(request.getPrice());
                response.setExchange(orderResponse.getExchange());
                return response;
            }

        }catch (JsonProcessingException e){e.printStackTrace();}

        ValidationResponse response = new ValidationResponse();
        response.setOrderID("");
        response.setOrderstatus("Order failed validation");
        response.setSide(request.getSide());
        response.setQuantity(request.getQuantity());
        response.setProduct(request.getProduct());
        response.setPrice(request.getPrice());
        response.setExchange("");
        return response;
    }

    public OrderResponse sendOrderRequest(ValidateOrder order){
        PostOrderService.orderService service = retrofit.create(PostOrderService.orderService.class);
        Call<OrderResponse> req = service.sendOrderRequest(order);
        try {
            return req.execute().body();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return new OrderResponse("","");
    }

//getting subscribed market data
    public Boolean validate(ValidateOrder request) throws JsonProcessingException {
      //  String datastring = jedis.rpop("MD");
       // if (datastring.isEmpty()) {
        //    return true;
        //}
       // var marketdata = objectMapper.readValue(datastring, new TypeReference<List<Trade>>() {});
        //System.out.println(marketdata.get(0).getTICKER());
        return true;
    }
}


