package com.exchangeconnectivity.exchangeserver.jedisconfig;


import com.exchangeconnectivity.exchangeserver.resourceclasses.TradeOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.JedisPubSub;


public class OrdersPubSub extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        System.out.println(message);

    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("Client is Subscribed to channel : "+ channel);
    }
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("Client is Unsubscribed from channel : "+ channel);
    }
}
