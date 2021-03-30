package com.exchangeconnectivity.exchangeserver;

import com.exchangeconnectivity.exchangeserver.resourceclasses.OpenOrder;
import com.exchangeconnectivity.exchangeserver.resourceclasses.Order;
import com.exchangeconnectivity.exchangeserver.resourceclasses.TradeOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;


@Service
public class PostOrderService {
    private TradeOrder order;
    private Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://exchange.matraining.com").build();
    private Retrofit retrofit2 = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://exchange2.matraining.com").build();
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    Jedis jedis;

    public PostOrderService(){}

    public void order() throws JsonProcessingException {
        while(true) {
            String json = jedis.rpop("Queue#pending");
            if(json != null) {
                deserializeJson(json);
                try {
                    String orderID = executeRequest();
                    if (orderID == null || orderID.isEmpty()) {
                        OpenOrder openOrder = new OpenOrder(order.getProduct(), order.getQuantity(), order.getPrice(), order.getSide(), order.getExchange(), order.getPortfolioID(), "failed", "", 0);
                        System.out.println("order failed");
                        jedis.publish("Channel#completed", serializeObject(openOrder));
                    }else {
                        OpenOrder openOrder = new OpenOrder(order.getProduct(), order.getQuantity(), order.getPrice(), order.getSide(), order.getExchange(), order.getPortfolioID(), "open", orderID, 0);
                        System.out.println("order succeeded");
                        jedis.publish("Channel#completed", serializeObject(openOrder));
                    }
                } catch (java.io.IOException e) { e.printStackTrace();}
            }
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){};
        }
    }

    private String executeRequest() throws IOException {
        Call<String> req = null;
        APIInterfaces.CreateOrderService service;
        service = this.order.getExchange().equals("exchange1")?(retrofit.create(APIInterfaces.CreateOrderService.class)):(retrofit2.create(APIInterfaces.CreateOrderService.class));
        req = service.createOrder(new Order(order.getProduct(),order.getQuantity(), order.getPrice(), order.getSide()));
        Response<String> res = req.execute();
        return res.body();
    }

    private void deserializeJson(String jsonString) throws JsonProcessingException {
       this.order =  objectMapper.readValue(jsonString, TradeOrder.class);
    }

    private String serializeObject(OpenOrder o) throws JsonProcessingException {
       return objectMapper.writeValueAsString(o);
    }

    public TradeOrder getOrder() {
        return order;
    }
}
