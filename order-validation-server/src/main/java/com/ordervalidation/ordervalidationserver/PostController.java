package com.ordervalidation.ordervalidationserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordervalidation.ordervalidationserver.jedisconfig.JedisConfig;
import com.ordervalidation.ordervalidationserver.marketdata.Marketdata;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.web.bind.annotation.*;

import static com.ordervalidation.ordervalidationserver.jedisconfig.JedisConfig.createJedisClient;

@RestController
public class PostController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @CrossOrigin
    @RequestMapping(value="/md", method= RequestMethod.POST, produces = "application/json", headers="Accept=*/*", consumes="application/json")
    @ResponseBody
    public Marketdata getMarketData(@RequestBody Marketdata md){
        var jedis= JedisConfig.createJedisClient();
        try {
             String stringifiedObject = objectMapper.writeValueAsString(md);
             jedis.lpush("MD", stringifiedObject);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return md;
    }
}
