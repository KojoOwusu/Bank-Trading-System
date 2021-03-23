package com.tradingsystem.reportingservice.jedisconfig;

import redis.clients.jedis.JedisPubSub;

public class OrdersPubSub extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        System.out.println(String.format("Channel: %s Message: %s",channel, message));
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
