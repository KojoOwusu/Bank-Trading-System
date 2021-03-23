package tradeengine.TradeServices.jedisconfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import tradeengine.TradeServices.Order;
import tradeengine.TradeServices.TradeOrder;

public class OrdersPubSub extends JedisPubSub {

    Jedis jedis = new Jedis();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onMessage(String channel, String message) {
       jedis.publish("Channel#processing", "Trade engine processing order");
       try {
           Order order = objectMapper.readValue(message, Order.class);
           //insert trade engine logic here
           TradeOrder tradeOrder = new TradeOrder(order.getProduct(), order.getQuantity(), order.getPrice(), order.getSide(), order.getPortfolioID(), order.getFunds(), order.getQuantityOwned(), "exchange1");
           //send on to queue
         //  jedis.lpush("Queue#pending", objectMapper.writeValueAsString(tradeOrder));
       }catch (JsonProcessingException e){};
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("subscribed to channel "+ channel);
    }
}
