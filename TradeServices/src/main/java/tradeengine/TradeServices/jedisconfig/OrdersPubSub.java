package tradeengine.TradeServices.jedisconfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import tradeengine.TradeServices.Order;
import tradeengine.TradeServices.TradeOrder;

public class OrdersPubSub extends JedisPubSub {
    @Autowired
    Jedis jedisclient;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onMessage(String channel, String message) {
        try {
            jedisclient.publish("Channel#processing", "Trade engine processing order");
            Order order = objectMapper.readValue(message, Order.class);


            //insert trade engine logic here

            TradeOrder tradeOrder = new TradeOrder(order.getProduct(),order.getQuantity(),order.getPrice(),order.getSide(), order.getPortfolioID(), order.getFunds(), order.getQuantityOwned(), "exchange1");
            //send on to queue
            jedisclient.lpush("Queue#pending",objectMapper.writeValueAsString(tradeOrder));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("subscribed to channel "+ channel);
    }
}
