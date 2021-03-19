package com.ordervalidation.ordervalidationserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordervalidation.ordervalidationserver.jedisconfig.JedisConfig;
import com.ordervalidation.ordervalidationserver.marketdata.Marketdata;
import com.ordervalidation.ordervalidationserver.marketdata.Trade;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ordervalidation.ordervalidationserver.jedisconfig.JedisConfig.createJedisClient;

@RestController
public class PostController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @CrossOrigin
    @RequestMapping(value="/md", method= RequestMethod.POST, produces = "application/json", headers="Accept=*/*", consumes="application/json")
    @ResponseBody
    public void getMarketData(@RequestBody List<Trade> md){
        var jedis= JedisConfig.createJedisClient();
        try {
             String stringifiedObject = objectMapper.writeValueAsString(md);
            System.out.println(stringifiedObject);
             jedis.lpush("MD", stringifiedObject);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
