package tradeengine.TradeServices.jedisconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class OrdersPubSub extends JedisPubSub {
    @Autowired
    Jedis jedisclient;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onMessage(String channel, String message) {
        objectMapper.readValue(message, )
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("subscribed to channel "+ channel);
    }
}
