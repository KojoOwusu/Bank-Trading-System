package com.ordervalidation.ordervalidationserver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.ordervalidation.ordervalidationserver.jedisconfig.JedisConfig;
import com.ordervalidation.ordervalidationserver.marketdata.MarketData;
import com.ordervalidation.ordervalidationserver.marketdata.OrderResponse;
import com.ordervalidation.ordervalidationserver.marketdata.Trade;
import com.ordervalidation.ordervalidationserver.validation.Validator;
import com.thoughtworks.qdox.model.expression.Or;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    MarketData MD;

    @Autowired
    Jedis jedis;
    private static final String NAMESPACE_URI = "http://turntabl/trading/ordervalidservice";


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "validateOrder")
    @ResponsePayload
    public ValidationResponse validateOrder(@RequestPayload ValidateOrder request) {
        if (validate(request)) {
            try {
                String jsonString = serializeObject(request);
                jedis.publish("Channel#tradeengine", jsonString);
            }catch(JsonProcessingException e){}
            ValidationResponse response = new ValidationResponse();
            response.setOrderstatus("Order validated successfully");
            return response;
        }
        ValidationResponse response = new ValidationResponse();
        response.setOrderstatus("Order validation failed");
        return response;

    }

    private String serializeObject(ValidateOrder o) throws JsonProcessingException {
            return objectMapper.writeValueAsString(o);
    }

//validating order
    private Boolean validate(ValidateOrder request)  {
    try {
        List<Trade> marketdata = MD.getData();
        var tradeObject = marketdata.stream().filter(x->x.getTICKER().equals(request.getProduct())).findFirst().get();
        Validator validator = new Validator(tradeObject, request);
        return validator.validate();
    }catch(NullPointerException e ){};
    return false;
    }
}


