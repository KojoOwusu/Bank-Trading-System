package tradeengine.TradeServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import java.util.List;


@RestController
public class TradeEngineRESTController {

    private Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create())).baseUrl("https://exchange-con.herokuapp.com").build();
    @Autowired
    Jedis jedisclient;


@RequestMapping(method=RequestMethod.POST, value="/api/trade", produces = "application/json", consumes= "application/json")
@ResponseBody
public tradeengine.TradeServices.Response receiveOrder(@RequestBody Order order) {
    TradeOrder orderwithexchange = new TradeOrder();
    orderwithexchange.setPrice(order.getPrice());
    orderwithexchange.setProduct(order.getProduct());
    orderwithexchange.setQuantity(order.getQuantity());
    orderwithexchange.setSide(order.getSide());
    orderwithexchange.setExchange("exchange1");    //this is where trade engine specifies exchange to trade on.

    //pubsub
    ObjectMapper objectMapper = new ObjectMapper();
    try {
        jedisclient.publish("OrdersChannel", objectMapper.writeValueAsString(orderwithexchange));   //sending it to pub sub on channel Orders Channel
    }catch(JsonProcessingException e){};

    //reequest to rest api
    var service = retrofit.create(OrderService.class);
    var req = service.createOrder(orderwithexchange);
    try {
//        return req.execute().body();

        Response<tradeengine.TradeServices.Response> res = req.execute();
        tradeengine.TradeServices.Response result = res.body();
        return result;
    } catch (java.io.IOException e) {
        e.printStackTrace();
    }
return new tradeengine.TradeServices.Response();
};

@RequestMapping(method = RequestMethod.POST, value="/md", consumes = "application/json")
@ResponseBody
public void receiveMarketData(@RequestBody List<TradeData> md){
    System.out.println(md);
}
}
